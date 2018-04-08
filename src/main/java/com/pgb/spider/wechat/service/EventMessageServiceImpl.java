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
public class EventMessageServiceImpl implements EventMessageService {
    private final static Logger logger = LoggerFactory.getLogger(EventMessageServiceImpl.class);

    @Autowired
    private SubscribeDao subscribeDao;


    @Override
    @Transactional
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
        // todo msgId未定义
        TextResponse response = new TextResponse();
        response.setToUserName(subscribe.getFromUserName());
        response.setFromUserName(subscribe.getToUserName());
        response.setCreateTime(System.currentTimeMillis() + "");
        response.setContent("欢迎订阅！此公众号为湖北经济学院毕业设计——大数据采集系统的职位查询公众号。\n" +
                "\n" +
                "如果您是首次订阅我的公众号，请点击个人中心设置您对为职位的要求，即可使用此公众号的功能。也可以编辑发送【职位名称】+【薪资要求】+【期望公司】来设置您的个人信息。设置好个人信息后，我们定期为您推送符合您要求的工作信息。\n" +
                "\n" +
                "下面是对此公众号功能的一些介绍。\n" +
                "【找工作】\n" +
                "    在您设置了个人信息后，点击找工作，即可获取满足您条件的一批工作。\n" +
                "【换一批】\n" +
                "    在您设置了个人信息后，点击换一批，即可获取不同的工作信息。\n" +
                "【个人中心】\n" +
                "    用于设置您的个人信息。");
        response.setMsgType(MsgType.text.getCode());
        response.setMsgId("2394792374");

        String result = JAXBUtils.marshal(response);

        logger.info("响应消息的内容是：" + result);


        return result;
    }
}
