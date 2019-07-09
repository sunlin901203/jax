package com.sunny.notify.controller;

import com.alibaba.fastjson.JSON;
import com.sunny.notify.api.NotifyApi;
import com.sunny.notify.enums.AppEnum;
import com.sunny.notify.enums.ResultEnum;
import com.sunny.notify.model.NotifyModel;
import com.sunny.notify.request.NotifyRequest;
import com.sunny.notify.response.NotifyResponse;
import com.sunny.notify.response.Response;
import com.sunny.notify.service.NotifyService;
import com.sunny.notify.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.common.Strings;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * NotifyApiController
 *
 * @author sunny
 * @version 1.0.0
 * @since 2018-12-25
 */
@Slf4j
@RestController
public class NotifyApiController implements NotifyApi {

    @Resource
    private NotifyService notifyService;

    @Override
    public Response<NotifyResponse> notify(@RequestBody NotifyRequest notifyRequest) {
        log.info("notify,notifyRequest={}", JSON.toJSONString(notifyRequest));

        // 验证参数
        Integer appId = notifyRequest.getAppId();
        if (appId == null) {
            return CommonUtil.fail(ResultEnum.PARAM_ERROR);
        }
        AppEnum appEnum = AppEnum.get(appId);
        if (appEnum == null) {
            return CommonUtil.fail(ResultEnum.PARAM_ERROR);
        }
        String sourceNotifyId = notifyRequest.getSourceNotifyId();
        if (Strings.isNullOrEmpty(sourceNotifyId)) {
            return CommonUtil.fail(ResultEnum.PARAM_ERROR);
        }
        String url = notifyRequest.getUrl();
        if (Strings.isNullOrEmpty(url)) {
            return CommonUtil.fail(ResultEnum.PARAM_ERROR);
        }
        String method = notifyRequest.getMethod();
        if (Strings.isNullOrEmpty(method)) {
            return CommonUtil.fail(ResultEnum.PARAM_ERROR);
        }
        String body = notifyRequest.getBody();

        // 设置默认数据
        Integer maxTime = notifyRequest.getMaxTime();
        if (maxTime == null || maxTime <= 0) {
            maxTime = 6;
        }

        // 构建对象
        NotifyModel notifyModel = new NotifyModel();
        notifyModel.setAppId(appId);
        notifyModel.setSourceNotifyId(sourceNotifyId);
        notifyModel.setUrl(url);
        notifyModel.setMethod(method);
        if (!Strings.isNullOrEmpty(body)){
            notifyModel.setBody(body);
        }
        notifyModel.setMaxTime(maxTime);

        // 保存
        Long id = notifyService.insert(notifyModel);

        NotifyResponse notifyResponse = new NotifyResponse();
        notifyResponse.setId(id);
        return CommonUtil.success(notifyResponse);
    }

//    @Override
//    public CommonResponse<PageResponse<ListVideoResponse>> select(NotifyRequest notifyRequest) {
//        return null;
//    }

}
