package com.pgb.spider.wechat.response;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

/**
 * @author Clayburn
 * @date : 2018/4/1 16:14
 * @description 点击菜单拉取消息时的时间推送
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "xml")
public class TouchMenuGetMessage implements Serializable {
    @XmlElement(name = "ToUserName")
    private String toUserName;
    @XmlElement(name = "FromUserName")
    private String fromUserName;
    @XmlElement(name = "CreateTime")
    private String createTime;
    @XmlElement(name = "MsgType")
    private String msgType;
    @XmlElement(name = "Content")
    private String content;
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
