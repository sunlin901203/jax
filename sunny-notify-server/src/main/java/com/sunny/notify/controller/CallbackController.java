package com.sunny.notify.controller;

import com.sunny.notify.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * CallbackController
 *
 * @author sunny
 * @version 1.0.0
 * @since 2019-04-11
 */
@Slf4j
@RestController
public class CallbackController {

    /**
     * 通知回调接口
     *
     * @return 结果
     */
    @RequestMapping(value = "/api/notify/callback", method = {RequestMethod.GET, RequestMethod.POST})
    public Response callback(@RequestBody String requestBody) {
        log.info("/api/notify/callback.requestBody={}", requestBody);
        return new Response();
    }

}
