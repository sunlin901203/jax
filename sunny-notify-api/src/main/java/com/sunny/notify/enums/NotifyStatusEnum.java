package com.sunny.notify.enums;

/**
 * NotifyStatusEnum
 *
 * @author sunny
 * @version 1.0.0
 * @since 2019-04-03
 */
@SuppressWarnings(value = "unused")
public enum NotifyStatusEnum {

    /**
     * 未通知
     */
    CREATE(1, "未通知", "未通知"),

    /**
     * 通知中
     */
    PROCESS(2, "通知中", "通知中，未成功"),

    /**
     * 通知成功
     */
    SUCCESS(3, "通知成功", "通知成功"),

    /**
     * 通知成通知失败功
     */
    FAIL(4, "通知失败", "通知失败"),

    ;

    private Integer code;
    private String message;
    private String description;

    /**
     * @param code        code
     * @param message     message
     * @param description description
     */
    NotifyStatusEnum(Integer code, String message, String description) {
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
    public static NotifyStatusEnum get(Integer code) {
        NotifyStatusEnum[] values = NotifyStatusEnum.values();
        NotifyStatusEnum v = null;
        for (NotifyStatusEnum value : values) {
            if (code.equals(value.getCode())) {
                v = value;
                break;
            }
        }
        return v;
    }

}
