package com.pgb.spider.wechat.controller;

import com.pgb.spider.wechat.xom.response.TextResponse;
import com.sun.xml.internal.bind.marshaller.CharacterEscapeHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    /**
     * 响应微信服务器发来的请求
     * xml的格式返回
     */
    @RequestMapping(path="/wechat", method = RequestMethod.POST)
    public void processWeChatRequest(HttpServletRequest request, HttpServletResponse response, @RequestBody String requestBody) throws Exception {
        logger.info("收到来自微信服务器的消息");
        logger.info(requestBody);
        TextResponse message = new TextResponse();
        message.setFromUserName("gh_95f3a5d4657d");
        message.setToUserName("omNBi0q0duJpkVVq-FrZBU0rJJvc");
        message.setCreateTime(System.currentTimeMillis() + ""); // 当前时间
        message.setContent("HELLO");
        message.setMsgId("234234234");
        message.setMsgType("text");

        JAXBContext jaxbContext = JAXBContext.newInstance(message.getClass());
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        // output pretty printed
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, true); // 去掉报头
        jaxbMarshaller.setProperty("com.sun.xml.internal.bind.marshaller.CharacterEscapeHandler",
                new CharacterEscapeHandler(){
                    @Override
                    public void escape(char[] ch, int start,
                                       int length, boolean isAttVal,
                                       Writer writer) throws IOException
                    {
                        writer.write(ch, start, length);
                    }
                });

        StringWriter writer = new StringWriter();
        try{
            jaxbMarshaller.marshal(message, writer);
            response.getWriter().write(writer.toString());
        } finally {
            writer.close();
        }
        //response.getWriter().write("已收到消息");
    }
}
