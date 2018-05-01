package com.pgb.spider.wechat.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Clayburn
 * @date : 2018/5/1 13:01
 * @description
 */
@Entity
@Table(name="collect")
public class Collect implements Serializable{
    @Id
    @GeneratedValue
    private Integer id;

    private String openid;

    private Integer jobId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }
}
