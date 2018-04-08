package com.pgb.spider.wechat.service;


/**
 * 用于处理文本消息
 */
public interface TextMessageService {

    /**
     * 处理文本消息
     * @param xml
     * @return
     */
    String dealWithText(String xml) throws Exception;
}
