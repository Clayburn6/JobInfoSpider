package com.pgb.spider.wechat.response;


import com.pgb.spider.wechat.xom.AdapterCDATA;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;

/**
 * @author Clayburn
 * @date : 2018/4/1 16:14
 * @description 点击菜单拉取消息时的时间推送
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "xml")
public class TouchMenuGetMessage implements Serializable {
    @XmlJavaTypeAdapter(AdapterCDATA.class)
    @XmlElement(name = "ToUserName")
    private String toUserName;
    @XmlJavaTypeAdapter(AdapterCDATA.class)
    @XmlElement(name = "FromUserName")
    private String fromUserName;
    @XmlJavaTypeAdapter(AdapterCDATA.class)
    @XmlElement(name = "CreateTime")
    private String createTime;
    @XmlJavaTypeAdapter(AdapterCDATA.class)
    @XmlElement(name = "MsgType")
    private String msgType;
    @XmlJavaTypeAdapter(AdapterCDATA.class)
    @XmlElement(name = "Content")
    private String content;
    @XmlJavaTypeAdapter(AdapterCDATA.class)
    @XmlElement(name = "MsgId")
    private String msgId;

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }
}
