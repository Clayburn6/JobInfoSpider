package com.pgb.spider.wechat.xom.request;

import com.pgb.spider.wechat.xom.AdapterCDATA;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;

/**
 * 自定义菜单点击事件消息类型
 */

@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "menu_click")
public class MenuClick implements Serializable {
    @Id
    @GeneratedValue
    @XmlTransient
    private Integer id;

    // 关注公众号人的微信号
    @XmlJavaTypeAdapter(AdapterCDATA.class)
    @XmlElement(name = "ToUserName")
    private String toUserName;

    // 关注公众号人对应这个公众号的openid
    @XmlJavaTypeAdapter(AdapterCDATA.class)
    @XmlElement(name = "FromUserName")
    private String fromUserName;

    // 创建事件
    @XmlJavaTypeAdapter(AdapterCDATA.class)
    @XmlElement(name = "CreateTime")
    private String createTime;

    // 消息类型 event
    @XmlJavaTypeAdapter(AdapterCDATA.class)
    @XmlElement(name = "MsgType")
    private String msgType;

    // 事件类型 subscribe(订阅)、unsubscribe(取消订阅)
    @XmlJavaTypeAdapter(AdapterCDATA.class)
    @XmlElement(name = "Event")
    private String event;

    // 与自定义菜单时的key对应
    @XmlJavaTypeAdapter(AdapterCDATA.class)
    @XmlElement(name = "EventKey")
    private String eventKey;

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

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }

    @Override
    public String toString() {
        return "MenuClick{" +
                "id=" + id +
                ", toUserName='" + toUserName + '\'' +
                ", fromUserName='" + fromUserName + '\'' +
                ", createTime='" + createTime + '\'' +
                ", msgType='" + msgType + '\'' +
                ", event='" + event + '\'' +
                ", eventKey='" + eventKey + '\'' +
                '}';
    }
}
