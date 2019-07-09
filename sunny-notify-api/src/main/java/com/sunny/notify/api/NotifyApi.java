package com.sunny.notify.api;

import com.sunny.notify.request.NotifyRequest;
import com.sunny.notify.response.NotifyResponse;
import com.sunny.notify.response.Response;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * NotifyApi
 *
 * @author sunny
 * @version 1.0.0
 * @since 2018-12-25
 */
public interface NotifyApi {

    /**
     * 通知
     *
     * @param notifyRequest 参数
     * @return 结果
     */
    @PostMapping(value = {"/api/notify/notify"})
    Response<NotifyResponse> notify(@RequestBody NotifyRequest notifyRequest);

//    /**
//     * 查询通知状态
//     *
//     * @param notifyRequest 参数
//     * @return 结果
//     */
//    @PostMapping(value = {"/api/notify/select"})
//    CommonResponse<PageResponse<ListVideoResponse>> select(@RequestBody NotifyRequest notifyRequest);

}
