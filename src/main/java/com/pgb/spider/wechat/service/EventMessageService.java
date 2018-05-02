package com.pgb.spider.wechat.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 微信服务器事件消息处理类
 */
@Service
public interface EventMessageService {
    /**
     * 处理事件消息
     * @param xml controller传来的xml格式的消息
     * @return 返回响应的内容
     */
    String dealWithEventMessage(String xml) throws Exception;

    String findWork(String title, Integer money, String company);
}
