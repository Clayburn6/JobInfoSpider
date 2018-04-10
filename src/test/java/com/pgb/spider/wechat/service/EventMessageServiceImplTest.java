package com.pgb.spider.wechat.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class EventMessageServiceImplTest {

    @Autowired
    private EventMessageService eventMessageService;

    @Test
    public void dealWithEventMessage() throws Exception {
        String str = eventMessageService.dealWithEventMessage("<xml><ToUserName><![CDATA[gh_95f3a5d4657d]]></ToUserName>\n" +
                "<FromUserName><![CDATA[omNBi0q0duJpkVVq-FrZBU0rJJvc]]></FromUserName>\n" +
                "<CreateTime>1523345731</CreateTime>\n" +
                "<MsgType><![CDATA[event]]></MsgType>\n" +
                "<Event><![CDATA[CLICK]]></Event>\n" +
                "<EventKey><![CDATA[VPGB_Find_Work]]></EventKey>\n" +
                "</xml>\n");

        Assert.assertNotNull(str);
    }

}