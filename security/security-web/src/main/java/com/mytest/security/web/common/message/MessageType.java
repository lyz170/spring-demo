package com.mytest.security.web.common.message;

/**
 * Created by Administrator on 2017/9/23.
 */
public enum MessageType  {

    SUCCESS("success"),
    INFO("info"),
    WARNING("warning"),
    ERROR("error"),
    DANGER("danger");

    private final String type;

    private MessageType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public String toString() {
        return this.type;
    }
}