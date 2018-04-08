package com.pgb.spider.wechat.service;


import com.pgb.spider.wechat.dao.TextRequestDao;
import com.pgb.spider.wechat.eumeration.MsgType;
import com.pgb.spider.wechat.xom.JAXBUtils;
import com.pgb.spider.wechat.xom.request.TextRequest;
import com.pgb.spider.wechat.xom.response.TextResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TextMessageServiceImpl implements TextMessageService {
    private static final Logger logger = LoggerFactory.getLogger(TextMessageServiceImpl.class);

    @Autowired
    private TextRequestDao textRequestDao;

    @Override
    public String dealWithText(String xml) throws Exception {
        logger.info("处理文本消息");
        TextRequest request = JAXBUtils.unmarshal(xml, TextRequest.class);

        logger.info("持久化文本消息, 内容：" + request.toString());
        textRequestDao.save(request);

        TextResponse response = new TextResponse();
        response.setToUserName(request.getFromUserName());
        response.setFromUserName(request.getToUserName());
        response.setCreateTime(System.currentTimeMillis() + "");
        response.setContent("正在开发中，请稍后");
        response.setMsgType(MsgType.text.getCode());
        response.setMsgId("2394792374");


        String result = JAXBUtils.marshal(response);

        logger.info("响应消息的内容是：" + result);


        return result;
    }
}
