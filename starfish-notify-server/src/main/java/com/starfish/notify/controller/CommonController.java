package com.starfish.notify.controller;

import com.starfish.notify.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * CommonController
 *
 * @author sunny
 * @version 1.0.0
 * @since 2018-12-25
 */
@Slf4j
@RestController
public class CommonController {

    /**
     * 健康检查接口
     *
     * @return 结果
     */
    @RequestMapping(value = "/api/alive", method = {RequestMethod.GET, RequestMethod.POST})
    public Response alive() {
        return new Response();
    }

}
