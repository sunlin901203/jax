package com.sunny.notify.controller;

import com.sunny.notify.model.NotifyModel;
import com.sunny.notify.response.Response;
import com.sunny.notify.service.NotifyService;
import com.sunny.notify.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * NotifyAdminController
 *
 * @author sunny
 * @version 1.0.0
 * @since 2019-04-03
 */
@Slf4j
@RestController
public class NotifyAdminController {

    @Resource
    private NotifyService notifyService;

    /**
     * 重新通知
     *
     * @param id 参数
     * @return 结果
     */
    @PostMapping(value = {"/api/admin/notify/retry"})
    public Response retry(Long id) {
        log.info("NotifyAdminController retry,id={}", id);

        NotifyModel notifyModel = new NotifyModel();
        notifyModel.setId(id);
        notifyService.retry(notifyModel);

        return CommonUtil.success();
    }

}
