package com.starfish.notify.enums;

/**
 * AppEnum
 *
 * @author sunny
 * @version 1.0.0
 * @since 2019-04-03
 */
@SuppressWarnings(value = "unused")
public enum AppEnum {

    /**
     * 视频审核
     */
    CREATE(1, "视频审核", "视频审核"),

    ;

    private Integer code;
    private String message;
    private String description;

    /**
     * @param code        code
     * @param message     message
     * @param description description
     */
    AppEnum(Integer code, String message, String description) {
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
    public static AppEnum get(Integer code) {
        AppEnum[] values = AppEnum.values();
        AppEnum v = null;
        for (AppEnum value : values) {
            if (code.equals(value.getCode())) {
                v = value;
                break;
            }
        }
        return v;
    }

}
