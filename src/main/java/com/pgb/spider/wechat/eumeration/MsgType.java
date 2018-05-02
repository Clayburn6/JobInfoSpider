package com.pgb.spider.wechat.eumeration;

import org.apache.commons.lang.StringUtils;

public enum MsgType {
    text("text", "文本消息"),
    event("event", "时间消息");

    private String code;
    private String description;

    private MsgType(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static MsgType fromCode(String code) {
        if (StringUtils.isBlank(code)) {
            throw new IllegalArgumentException("参数能为空");
        }
        for (MsgType msgType : values()) {
            if (msgType.getCode().equals(code)) {
                return msgType;
            }
        }
        throw new IllegalArgumentException("参数异常，无法完成转换");
    }
}
