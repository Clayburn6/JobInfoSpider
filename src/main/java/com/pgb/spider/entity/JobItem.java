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
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "title")
    private String title;
    @Column(name = "money")
    private String money;
    @Column(name = "min_money")
    private Integer minMoney;
    @Column(name = "max_money")
    private Integer maxMoney;
    @Column(name = "company")
    private String company;
    @Column(name = "place")
    private String place;
    @Lob
    @Column(name = "description", length = 16777215)
    private String desc;
    @Column(name = "nature")
    private String nature;//性质
    @Column(name = "experience")
    private String experience; //经验
    @Column(name = "degree")
    private String degree; //学历
    @Column(name = "type")
    private String type; // 职位类别
    @Column(name = "url")
    private String url;
    @Column(name = "from_where")
    private String fromWhere;
    @Column(name = "job_advantage")
    private String jobAdvantage; // 职位诱惑
    @Lob
    @Column(name = "job_bt", length = 16777215)
    private String jobBt; // 岗位职责
    @Column(name = "job_address")
    private String jobAddress;

    public String getJobAdvantage() {
        return jobAdvantage;
    }

    public void setJobAdvantage(String jobAdvantage) {
        this.jobAdvantage = jobAdvantage;
    }

    public String getJobBt() {
        return jobBt;
    }

    public void setJobBt(String jobBt) {
        this.jobBt = jobBt;
    }

    public String getJobAddress() {
        return jobAddress;
    }

    public void setJobAddress(String jobAddress) {
        this.jobAddress = jobAddress;
    }

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
