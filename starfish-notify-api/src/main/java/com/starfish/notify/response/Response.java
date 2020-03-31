package com.starfish.notify.response;

import com.starfish.notify.exception.CustomException;
import com.starfish.notify.enums.ResultEnum;

import java.io.Serializable;

/**
 * Response
 *
 * @author sunny
 * @version 1.0.0
 * @since 2019-07-09
 */
public class Response<T> implements Serializable {

    /**
     * status
     */
    private Integer status;

    /**
     * message
     */
    private String message;

    /**
     * body
     */
    private T body;

    public Response() {
        this.status = 0;
        this.message = "success";
    }

    @SuppressWarnings(value = "unchecked")
    public Response(Object body) {
        this.status = 0;
        this.message = "success";
        this.body = (T) body;
    }

    public Response(ResultEnum resultEnum) {
        this.status = resultEnum.getCode();
        this.message = resultEnum.getMessage();
    }

    public Response(CustomException exception) {
        this.status = exception.getCode();
        this.message = exception.getMessage();
    }

    public Response(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

}
