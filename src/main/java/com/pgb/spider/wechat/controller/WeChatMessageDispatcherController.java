package com.pgb.spider.wechat.controller;

import com.pgb.spider.wechat.dom.DomUtils;
import com.pgb.spider.wechat.eumeration.MsgType;
import com.pgb.spider.wechat.service.EventMessageService;
import com.pgb.spider.wechat.service.TextMessageService;
import com.pgb.spider.wechat.xom.request.TextRequest;
import com.pgb.spider.wechat.xom.response.TextResponse;
import com.sun.xml.internal.bind.marshaller.CharacterEscapeHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

/**
 * 用来转发来自微信服务器的xml格式的消息
 */

@Controller
public class WeChatMessageDispatcherController {
    private static final Logger logger = LoggerFactory.getLogger(WeChatMessageDispatcherController.class);

    @Autowired
    private EventMessageService eventMessageService;

    @Autowired
    private TextMessageService textMessageService;

    /**
     * 响应微信服务器发来的请求
     * xml的格式返回
     */
    @RequestMapping(path="/wechat", method = RequestMethod.POST)
    public void processWeChatRequest(HttpServletRequest request, HttpServletResponse response, @RequestBody String requestBody) throws Exception {
        logger.info("收到来自微信服务器的消息");

        // 开始dom解析,将消息进行分类
        String code = DomUtils.selectXmlByTagName(requestBody, "MsgType"); // 查看消息类型
        MsgType msgType = MsgType.fromCode(code);
        String result = "";
        switch (msgType) {
            case event: // 事件消息
                logger.debug(result);
                result = eventMessageService.dealWithEventMessage(requestBody);
                logger.debug(result);
                break;
            case text: // 文本消息
                logger.debug(result);
                result = textMessageService.dealWithText(requestBody);
                logger.debug(result);
                break;
            default: // 回复"success"
                result = "success";
        }
        // 设置编码
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(result);
    }
}
