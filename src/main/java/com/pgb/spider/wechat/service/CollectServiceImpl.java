package com.pgb.spider.wechat.service;

import com.pgb.spider.entity.JobItem;
import com.pgb.spider.web.dao.QueryDao;
import com.pgb.spider.web.utils.Pagination;
import com.pgb.spider.wechat.dao.CollectDao;
import com.pgb.spider.wechat.entity.Collect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Clayburn
 * @date : 2018/5/1 13:09
 * @description
 */

@Service
public class CollectServiceImpl implements CollectService {
    @Autowired
    private CollectDao collectDao;

    @Autowired
    private QueryDao queryDao;


    @Override
    @Transactional
    public void collectByOpenidAndJobId(String openid, Integer jobId) {
        if (checkCollect(openid, jobId)) {
            return;
        }

        // 添加收藏
        Collect collect = new Collect();
        collect.setJobId(jobId);
        collect.setOpenid(openid);

        collectDao.save(collect);

    }

    @Override
    @Transactional
    public void unCollectByOpenidAndJobId(String openid, Integer jobId) {
        collectDao.deleteByOpenidAndJobId(openid, jobId);
    }

    @Override
    @Transactional
    public boolean checkCollect(String openid, Integer jobId) {
        List<Collect> collectList = collectDao.findAllByOpenid(openid);

        if (collectList == null || collectList.isEmpty()) {
            return false;
        }

        // 查看是否已经被收藏
        for (Collect collect : collectList) {
            if (collect.getJobId().equals(jobId)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<JobItem> getCollect(String openid) {
        List<Collect> collects = collectDao.findAllByOpenid(openid);

        if (collects == null || collects.isEmpty()) {
            return Collections.EMPTY_LIST;
        }

        List<Integer> jobIds = new ArrayList<Integer>();
        for (Collect collect : collects) {
            jobIds.add(collect.getJobId());
        }

        List<JobItem> jobItems = queryDao.getByIdIn(jobIds);
        if (jobItems == null || jobItems.isEmpty()) {
            return Collections.EMPTY_LIST;
        }
        return jobItems;
    }
}
