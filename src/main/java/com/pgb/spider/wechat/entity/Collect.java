package com.pgb.spider.wechat.entity;

import javax.persistence.*;
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

    @Column(name = "openid")
    private String openid;

    @Column(name = "job_id")
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
