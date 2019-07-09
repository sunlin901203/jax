package com.sunny.notify.util;

import com.sunny.notify.enums.ResultEnum;
import com.sunny.notify.response.Response;

/**
 * CommonUtil
 *
 * @author sunny
 * @version 1.0.0
 * @since 2018-12-26
 */
public class CommonUtil {

    /**
     * 返回成功结果
     *
     * @return 结果
     */
    public static Response success() {
        return new Response<>();
    }

    /**
     * 成功
     *
     * @param data 数据
     * @param <T>  T
     * @return 结果
     */
    public static <T> Response<T> success(T data) {
        return new Response<>(data);
    }

    /**
     * 返回失败结果
     *
     * @param resultEnum 枚举
     * @param <T>        类型
     * @return 结果
     */
    @SuppressWarnings("all")
    public static <T> Response<T> fail(ResultEnum resultEnum) {
        Response result = new Response<>();
        result.setStatus(resultEnum.getCode());
        result.setMessage(resultEnum.getMessage());
        return (Response<T>) result;
    }

    /**
     * 判断结果是否是失败
     *
     * @param response 参数
     * @return 结果
     */
    public static boolean fail(Response response) {
        return !success(response);
    }

    /**
     * 判断结果是否是成功
     *
     * @param response 参数
     * @return true成功，false失败
     */
    public static boolean success(Response response) {
        return response != null
                && response.getStatus() != null
                && response.getStatus().equals(ResultEnum.SUCCESS.getCode());
    }

}
