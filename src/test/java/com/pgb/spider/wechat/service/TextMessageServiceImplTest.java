package com.pgb.spider.wechat.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;


@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TextMessageServiceImplTest {
    @Autowired
    private TextMessageService textMessageService;

    @Test
    public void dealWithText() throws Exception {
        textMessageService.dealWithText("<xml>\n" +
                "    <ToUserName><![CDATA[omNBi0q0duJpkVVq-FrZBU0rJJvc]]></ToUserName>\n" +
                "    <FromUserName><![CDATA[gh_95f3a5d4657d]]></FromUserName>\n" +
                "    <CreateTime><![CDATA[1523175665194]]></CreateTime>\n" +
                "    <MsgType><![CDATA[text]]></MsgType>\n" +
                "    <Content><![CDATA[正在开发中，请稍后]]></Content>\n" +
                "    <MsgId><![CDATA[2394792374]]></MsgId>\n" +
                "</xml>\n");
    }

}