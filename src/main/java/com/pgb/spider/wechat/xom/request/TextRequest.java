package com.pgb.spider.wechat.xom.request;

import com.pgb.spider.wechat.xom.AdapterCDATA;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "xml")
@Entity
@Table(name = "text_request")
public class TextRequest implements Serializable {

    @Id
    @GeneratedValue
    @XmlTransient
    private Integer id;

    // 接受方的微信号
    @XmlJavaTypeAdapter(AdapterCDATA.class)
    @XmlElement(name = "ToUserName")
    private String toUserName;

    // 接收方的openid
    @XmlJavaTypeAdapter(AdapterCDATA.class)
    @XmlElement(name = "FromUserName")
    private String fromUserName;

    // 创建时间
    @XmlJavaTypeAdapter(AdapterCDATA.class)
    @XmlElement(name = "CreateTime")
    private String createTime;

    // text
    @XmlJavaTypeAdapter(AdapterCDATA.class)
    @XmlElement(name = "MsgType")
    private String msgType;

    // 消息内容
    @XmlJavaTypeAdapter(AdapterCDATA.class)
    @XmlElement(name = "Content")
    private String content;

    // 消息的唯一标识
    @XmlJavaTypeAdapter(AdapterCDATA.class)
    @XmlElement(name = "MsgId")
    private String msgId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        return "TextRequest{" +
                "id=" + id +
                ", toUserName='" + toUserName + '\'' +
                ", fromUserName='" + fromUserName + '\'' +
                ", createTime='" + createTime + '\'' +
                ", msgType='" + msgType + '\'' +
                ", content='" + content + '\'' +
                ", msgId='" + msgId + '\'' +
                '}';
    }
}
