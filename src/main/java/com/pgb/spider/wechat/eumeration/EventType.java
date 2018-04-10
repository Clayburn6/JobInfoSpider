package com.pgb.spider.wechat.eumeration;

import org.apache.commons.lang.StringUtils;

public enum EventType {
    subscribe("subscribe", "订阅事件"), // 订阅
    unsubscribe("unsubscribe", "取消订阅事件"), // 取消订阅
    CLICK("CLICK", "自定义菜单事件"),
    VIEW("VIEW", "跳转网页");

    private String code;
    private String description;

    private EventType(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static EventType fromCode(String code) {
        if (StringUtils.isBlank(code)) {
            throw new IllegalArgumentException("参数能为空");
        }
        for (EventType eventType : values()) {
            if (eventType.getCode().equals(code)) {
                return eventType;
            }
        }
        throw new IllegalArgumentException("参数异常，无法完成转换");
    }
}
