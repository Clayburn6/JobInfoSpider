package com.pgb.spider.wechat.entity;

import java.io.Serializable;

/**
 * @author Clayburn
 * @date : 2018/5/3 11:45
 * @description
 */
public class Text implements Serializable {
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
