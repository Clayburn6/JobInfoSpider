package com.pgb.spider.wechat.service;

import com.pgb.spider.entity.JobItem;
import com.pgb.spider.web.utils.Pagination;

import java.util.List;

/**
 * @author Clayburn
 * @date : 2018/5/1 13:06
 * @description
 */
public interface CollectService {
    void collectByOpenidAndJobId(String openid, Integer jobId);
    void unCollectByOpenidAndJobId(String openid, Integer jobId);
    boolean checkCollect(String openid, Integer jobId);
    List<JobItem> getCollect(String openid);
}
