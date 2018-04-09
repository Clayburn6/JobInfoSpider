package com.pgb.spider.wechat.service;

import com.pgb.spider.wechat.dao.SubscribeDao;
import com.pgb.spider.wechat.dao.UserInfoDao;
import com.pgb.spider.wechat.dom.DomUtils;
import com.pgb.spider.wechat.entity.UserInfo;
import com.pgb.spider.wechat.eumeration.Constant;
import com.pgb.spider.wechat.eumeration.EventKey;
import com.pgb.spider.wechat.eumeration.EventType;
import com.pgb.spider.wechat.eumeration.MsgType;
import com.pgb.spider.wechat.xom.JAXBUtils;
import com.pgb.spider.wechat.xom.request.MenuClick;
import com.pgb.spider.wechat.xom.request.Subscribe;
import com.pgb.spider.wechat.xom.response.TextResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Date;

@Service
public class EventMessageServiceImpl implements EventMessageService {
    private final static Logger logger = LoggerFactory.getLogger(EventMessageServiceImpl.class);

    @Autowired
    private SubscribeDao subscribeDao;

    @Autowired
    private UserInfoDao userInfoDao;


    @Override
    @Transactional
    public String dealWithEventMessage(String xml) throws Exception {
        // 解析消息，分一下是那种类型的事件消息
        String code = DomUtils.selectXmlByTagName(xml, "Event");
        EventType event = EventType.fromCode(code);
        switch (event) {
            case subscribe:
                return dealWithSubScribe(xml);
            case unsubscribe: // 取消订阅，删除该用户
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
        logger.info("持久化订阅消息, 内容：\n" + subscribe.toString());
        subscribeDao.save(subscribe);
        // 生成xml消息
        TextResponse response = new TextResponse();
        response.setToUserName(subscribe.getFromUserName());
        response.setFromUserName(subscribe.getToUserName());
        response.setCreateTime(System.currentTimeMillis() + "");
        response.setContent(Constant.WELCOME_INFO);
        response.setMsgType(MsgType.text.getCode());
        response.setMsgId(System.currentTimeMillis() + "");

        String result = JAXBUtils.marshal(response);

        logger.info("响应消息, msgId = " + response.getMsgId());


        return result;
    }

    /**
     * 取消订阅
     * @param xml
     * @return
     * @throws JAXBException
     */
    private String dealWithUnSubcribe(String xml) throws JAXBException {
        Subscribe subscribe = JAXBUtils.unmarshal(xml, Subscribe.class);
        if (subscribe == null) {
            throw new RuntimeException("消息解析失败！");
        }
        logger.info("取消订阅事件， openid = " + subscribe.getFromUserName() + ", 微信号 = " + subscribe.getToUserName());

        subscribeDao.save(subscribe);

        UserInfo userInfo = userInfoDao.findByWechatCodeAndOpenid(subscribe.getToUserName(), subscribe.getFromUserName());
        userInfo.setDeleteFlag(true);
        logger.info("用户已逻辑删除删除， openid = " + subscribe.getFromUserName() + ", 微信号 = " + subscribe.getToUserName());

        return "success";
    }

    /**
     * 自定义菜单点击事件
     * @param xml
     * @return
     * @throws JAXBException
     */
    private String dealWithClick(String xml) throws JAXBException {
        MenuClick menuClick = JAXBUtils.unmarshal(xml, MenuClick.class);

        EventKey eventKey = EventKey.fromCode(menuClick.getEventKey());

        UserInfo userInfo = userInfoDao.findByWechatCodeAndOpenid(menuClick.getToUserName(), menuClick.getFromUserName());

        // 生成xml消息
        TextResponse response = new TextResponse();
        response.setToUserName(menuClick.getFromUserName());
        response.setFromUserName(menuClick.getToUserName());
        response.setCreateTime(System.currentTimeMillis() + "");
        response.setMsgType(MsgType.text.getCode());
        response.setMsgId(System.currentTimeMillis() + "");

        if (userInfo == null) {
            logger.info("用户未设置个人信息，提示用户先设置个人信息");
            response.setContent(Constant.WARNING_SET_USERINFO);
        }

        switch (eventKey) {
            case VPGB_Find_Work:
                return "success";
            case VPGB_Refresh_Work:
                return "success";
            default:
                return "success";
        }
    }
}
