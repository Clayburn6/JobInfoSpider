package com.pgb.spider.entity;

import javax.persistence.*;

/**
 * @author Clayburn
 * @date : 2018/1/22 22:06
 * @description
 */
@Entity
@Table(name = "job_info")
public class JobItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String title;
    private String money;
    private Integer minMoney;
    private Integer maxMoney;
    private String company;
    private String place;
    @Lob
    @Column(name = "description", length = 16777215)
    private String desc;
    private String nature;//性质
    private String experience; //经验
    private String degree; //学历
    private String type; // 职位类别
    private String url;
    private String fromWhere;

    public Integer getMaxMoney() {
        return maxMoney;
    }

    public void setMaxMoney(Integer maxMoney) {
        this.maxMoney = maxMoney;
    }

    public String getFromWhere() {
        return fromWhere;
    }

    public void setFromWhere(String fromWhere) {
        this.fromWhere = fromWhere;
    }

    public Integer getMinMoney() {
        return minMoney;
    }

    public void setMinMoney(Integer minMoney) {
        this.minMoney = minMoney;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "JobItem{" +
                "title='" + title + '\'' +
                ", money='" + money + '\'' +
                ", company='" + company + '\'' +
                ", place='" + place + '\'' +
                ", desc='" + desc + '\'' +
                ", nature='" + nature + '\'' +
                ", experience='" + experience + '\'' +
                ", degree='" + degree + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
