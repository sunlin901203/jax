package com.sunny.notify.enums;

/**
 * 结果枚举
 *
 * @author sunny
 * @version 1.0.0
 * @since 2014-07-07
 */
@SuppressWarnings(value = "unused")
public enum ResultEnum {

    /**
     * 成功
     */
    SUCCESS(200, "成功", "成功"),

    SYSTEM_EXCEPTION(500, "系统异常", "系统异常"),

    /**
     * 部分统一的错误从800开始
     */
    PARAM_ERROR(800, "参数错误", "参数错误"),

    /**
     * 自定义错误从1001开始
     */
    DATE_TIME_FORMAT_ERROR(1001, "日期格式有误", "日期格式有误"),

    NO_NOTIFY_ERROR(1002, "无此通知", "无此通知"),

    ;

    private Integer code;
    private String message;
    private String description;

    /**
     * @param code        code
     * @param message     message
     * @param description description
     */
    ResultEnum(Integer code, String message, String description) {
        this.code = code;
        this.message = message;
        this.description = description;
    }

    /**
     * get the enum code
     *
     * @return code
     */
    public Integer getCode() {
        return this.code;
    }

    /**
     * get the message
     *
     * @return message
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * get the enum description
     *
     * @return description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * get the enum by code
     *
     * @param code code
     * @return the enum
     */
    public static ResultEnum get(Integer code) {
        ResultEnum[] values = ResultEnum.values();
        ResultEnum v = null;
        for (ResultEnum value : values) {
            if (code.equals(value.getCode())) {
                v = value;
                break;
            }
        }
        return v;
    }

}
