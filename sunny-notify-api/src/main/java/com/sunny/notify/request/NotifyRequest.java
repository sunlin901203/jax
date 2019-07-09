package com.sunny.notify.request;

import lombok.Data;

import java.io.Serializable;

/**
 * NotifyRequest
 *
 * @author sunny
 * @version 1.0.0
 * @since 2019-04-03
 */
@Data
public class NotifyRequest implements Serializable {

    private Integer appId;

    private String sourceNotifyId;

    private String url;

    private String method;

    private String body;

    private Integer maxTime;

}