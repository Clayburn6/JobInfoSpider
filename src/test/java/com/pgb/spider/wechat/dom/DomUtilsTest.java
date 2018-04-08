package com.pgb.spider.wechat.dom;

import org.junit.Test;

import static org.junit.Assert.*;

public class DomUtilsTest {
    @Test
    public void selectXmlByTagName() throws Exception {
        String xml = "<xml><ToUserName><![CDATA[gh_95f3a5d4657d]]></ToUserName>\n" +
                "<FromUserName><![CDATA[omNBi0q0duJpkVVq-FrZBU0rJJvc]]></FromUserName>\n" +
                "<CreateTime>1523163854</CreateTime>\n" +
                "<MsgType><![CDATA[event]]></MsgType>\n" +
                "<Event><![CDATA[CLICK]]></Event>\n" +
                "<EventKey><![CDATA[VPGB_Find_Work]]></EventKey>\n" +
                "</xml>\n";

        String tagName = "Event";

        System.out.println(DomUtils.selectXmlByTagName(xml, tagName));
    }

}