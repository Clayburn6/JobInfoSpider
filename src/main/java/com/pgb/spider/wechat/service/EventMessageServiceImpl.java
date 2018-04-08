package com.pgb.spider.wechat.service;

import com.pgb.spider.wechat.dao.SubscribeDao;
import com.pgb.spider.wechat.dom.DomUtils;
import com.pgb.spider.wechat.eumeration.EventType;
import com.pgb.spider.wechat.eumeration.MsgType;
import com.pgb.spider.wechat.xom.JAXBUtils;
import com.pgb.spider.wechat.xom.request.Subscribe;
import com.pgb.spider.wechat.xom.response.TextResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.JAXBException;
import java.util.Date;

@Service
@Transactional
public class EventMessageServiceImpl implements EventMessageService {
    private final static Logger logger = LoggerFactory.getLogger(EventMessageServiceImpl.class);

    @Autowired
    private SubscribeDao subscribeDao;


    @Override
    public String dealWithEventMessage(String xml) throws Exception {
        // 解析消息，分一下是那种类型的事件消息
        String code = DomUtils.selectXmlByTagName(xml, "Event");
        EventType event = EventType.fromCode(code);
        switch (event) {
            case subscribe:
                return dealWithSubScribe(xml);
            case unsubscribe: // 取消订阅，暂不做处理
                return "success";
            case CLICK:
                break;
            default:
                return "success";
        }
        return null;
    }

    /**
     * 订阅事件
     * @param xml
     * @return
     */

    private String dealWithSubScribe(String xml) throws JAXBException {
        logger.info("处理订阅事件");
        // 用jaxb将String转成object
        Subscribe subscribe = JAXBUtils.unmarshal(xml, Subscribe.class);
        logger.info("持久化订阅消息, 内容：" + subscribe.toString());
        subscribeDao.save(subscribe);
        // todo 将微信号和openid单独存储，用于群发消息时使用
        // 生成xml消息
        // todo 订阅的消息模板尚未完成, msgId未定义
        TextResponse response = new TextResponse();
        response.setToUserName(subscribe.getFromUserName());
        response.setFromUserName(subscribe.getToUserName());
        response.setCreateTime(System.currentTimeMillis() + "");
        response.setContent("欢迎订阅");
        response.setMsgType(MsgType.text.getCode());
        response.setMsgId("2394792374");

        String result = JAXBUtils.marshal(response);

        logger.info("响应消息的内容是：" + result);


        return result;
    }
}
