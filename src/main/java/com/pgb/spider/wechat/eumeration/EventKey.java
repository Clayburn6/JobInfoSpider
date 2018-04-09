package com.pgb.spider.wechat.eumeration;

import org.apache.commons.lang.StringUtils;

/**
 * 自定义菜单click种类的key
 */
public enum EventKey {
    VPGB_Find_Work("VPGB_Find_Work", "找工作"),
    VPGB_Refresh_Work("VPGB_Refresh_Work", "换一批");

    private String code;
    private String description;

    private EventKey(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static EventKey fromCode(String code) {
        if (StringUtils.isBlank(code)) {
            throw new IllegalArgumentException("参数能为空");
        }
        for (EventKey eventKey : values()) {
            if (eventKey.getCode().equals(code)) {
                return eventKey;
            }
        }
        throw new IllegalArgumentException("参数异常，无法完成转换");
    }
}
