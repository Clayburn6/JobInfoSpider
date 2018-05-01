package com.pgb.spider.wechat.service;

import com.pgb.spider.wechat.dao.CollectDao;
import com.pgb.spider.wechat.entity.Collect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
            if (collect.getJobId() == jobId) {
                return true;
            }
        }
        return false;
    }
}
