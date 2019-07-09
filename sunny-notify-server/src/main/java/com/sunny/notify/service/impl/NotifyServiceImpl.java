package com.sunny.notify.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sunny.notify.enums.NotifyStatusEnum;
import com.sunny.notify.enums.ResultEnum;
import com.sunny.notify.exception.CustomException;
import com.sunny.notify.mapper.NotifyMapper;
import com.sunny.notify.model.NotifyModel;
import com.sunny.notify.service.NotifyService;
import com.sunny.notify.util.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * NotifyServiceImpl
 *
 * @author sunny
 * @version 1.0.0
 * @since 2019-04-03
 */
@Slf4j
@Service
public class NotifyServiceImpl implements NotifyService {

    @Resource
    private ThreadPoolTaskExecutor executor;

    @Resource
    private NotifyMapper notifyMapper;

    @Override
    public Long insert(NotifyModel notifyModel) {
        // 设置默认数据
        notifyModel.setTime(0);
        notifyModel.setStatus(NotifyStatusEnum.CREATE.getCode());
        notifyModel.setCreateTime(new Date());
        notifyModel.setModifyTime(new Date());

        notifyMapper.insert(notifyModel);
        return notifyModel.getId();
    }

    @Override
    public void notifyTask() {
        // 查询数据通知中的数据
        List<NotifyModel> notifyModelList = new ArrayList<>(2000);

        NotifyModel notifyParam = new NotifyModel();
        notifyParam.setStatus(NotifyStatusEnum.CREATE.getCode());
        List<NotifyModel> list1 = notifyMapper.list(notifyParam);

        notifyParam.setStatus(NotifyStatusEnum.PROCESS.getCode());
        List<NotifyModel> list2 = notifyMapper.list(notifyParam);

        notifyModelList.addAll(list1);
        notifyModelList.addAll(list2);
        log.info("notifyModelList size={}", notifyModelList.size());
        if (CollectionUtils.isEmpty(notifyModelList)) {
            return;
        }

        // 通知
        for (NotifyModel notifyModel : notifyModelList) {
            this.notify(notifyModel);
        }
    }

    @Override
    public void notify(NotifyModel notifyModel) {
        executor.submit(new NotifyRunnable(notifyModel));
    }

    @Override
    public void retry(NotifyModel notifyModel) {
        // 根据id查询
        Long notifyId = notifyModel.getId();
        NotifyModel result = notifyMapper.selectByPrimaryKey(notifyId);

        if (result == null) {
            throw new CustomException(ResultEnum.NO_NOTIFY_ERROR);
        }

        executor.submit(new NotifyRunnable(result));
    }

    private class NotifyRunnable implements Runnable {

        private NotifyModel notifyModel;

        NotifyRunnable(NotifyModel notifyModel) {
            this.notifyModel = notifyModel;
        }

        @Override
        public void run() {
            // 更新状态
            Integer status = notifyModel.getStatus();
            if (NotifyStatusEnum.CREATE.getCode().equals(status)) {
                notifyModel.setStatus(NotifyStatusEnum.PROCESS.getCode());
            }
            notifyModel.setNotifyTime(new Date());
            notifyModel.setModifyTime(new Date());
            Integer time = notifyModel.getTime();
            notifyModel.setTime(time + 1);
            notifyMapper.updateByPrimaryKeySelective(notifyModel);

            // 发送
            String url = notifyModel.getUrl();
            String method = notifyModel.getMethod().toLowerCase();
            String body = notifyModel.getBody();

            HttpResponse response = null;
            try {

                JSONObject jsonObjectBody = JSON.parseObject(body);

                if ("get".equalsIgnoreCase(method)) {
                    response = HttpUtil.getWithReturnResponse(url);
                } else if ("post".equalsIgnoreCase(method)) {
                    response = HttpUtil.doPostWithReturnResponse(url, jsonObjectBody);
                }

                log.info("notify result,response statusCode={}", response.getStatusLine().getStatusCode());
            } catch (Exception e) {
                log.error("notify error.notifyModel={}", JSON.toJSONString(notifyModel), e);
            }

            // 判断返回状态是否成功，如果成功，更新状态为成功
            if (response == null || response.getStatusLine().getStatusCode() != HttpStatus.OK.value()) {

                // 更新失败状态
                Integer maxTime = notifyModel.getMaxTime();
                if (time >= maxTime) {
                    notifyModel.setStatus(NotifyStatusEnum.FAIL.getCode());
                    notifyModel.setNotifyTime(new Date());
                    notifyModel.setModifyTime(new Date());
                    notifyMapper.updateByPrimaryKeySelective(notifyModel);
                }

                return;
            }

            notifyModel.setStatus(NotifyStatusEnum.SUCCESS.getCode());
            notifyModel.setNotifyTime(new Date());
            notifyModel.setModifyTime(new Date());
            notifyMapper.updateByPrimaryKeySelective(notifyModel);
        }
    }

}
