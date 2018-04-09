package com.pgb.spider.store;

import com.pgb.spider.entity.JobItem;
import com.pgb.spider.executer.response.TaskResponse;
import com.pgb.spider.utils.SpiderUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * @author Clayburn
 * @date : 2018/1/16 19:45
 * @description
 */
public class LagouStore implements IStore {
    @Override
    public void store(TaskResponse response) throws IOException {
//        System.out.println(response.getContent());

//        String url = "https://www.lagou.com/jobs/3891964.html?source=pl&i=pl-0";
//        Document doc = Jsoup.connect(url)
//                .data("query", "Java")
//                .userAgent("Mozilla")
//                .cookie("auth", "token")
//                .timeout(3000).get();

        Document doc = response.getDocument();
        String title = doc.select(".job-name").attr("title");
        String company = doc.select(".job-name").select(".company").text();

        String money = "";
        String place = "";
        String exprience = "";
        String degree = "";
        String type = "";
        Elements jobInfos = doc.select(".job_request").select("p").select("span");
        if (jobInfos.size() >= 5) {
            money = jobInfos.get(0).text();
            place = jobInfos.get(1).text();
            exprience = jobInfos.get(2).text();
            degree = jobInfos.get(3).text();
            type = jobInfos.get(4).text();
        }

        Elements tabInnerBox = doc.select("#job_detail").select("dd");
        String desc = tabInnerBox.text();

        JobItem jobItem = new JobItem();
        jobItem.setTitle(title);
        jobItem.setCompany(company);
        jobItem.setMoney(money);
        jobItem.setPlace(place);
        jobItem.setDesc(desc);
        jobItem.setExperience(exprience);
        jobItem.setDegree(degree);
        jobItem.setType(type);
        jobItem.setUrl(response.getTask().getUrl());
        jobItem.setFromWhere("Lagou");
        System.out.println(jobItem);

        // 设置薪资范围
        money = money.toLowerCase();
        if (money.matches("(\\d)+k-(\\d)+k")) {
            String[] strArray = money.split("-");
            if (strArray != null && strArray.length == 2) {
                jobItem.setMinMoney(Integer.parseInt(strArray[0].replace("k", "")));
                jobItem.setMaxMoney(Integer.parseInt(strArray[1].replace("k", "")));
            }
        }

        Session session = SpiderUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(jobItem);
        transaction.commit();;
        session.close();
    }
}
