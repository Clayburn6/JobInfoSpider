package com.pgb.spider.wechat.dao;

import com.pgb.spider.wechat.entity.Collect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

/**
 * @author Clayburn
 * @date : 2018/5/1 13:03
 * @description
 */
public interface CollectDao extends JpaRepository<Collect, Integer>{
    List<Collect> findAllByOpenid(String openid);

    @Modifying
    void deleteByOpenidAndJobId(String openid, Integer jobId);
}
