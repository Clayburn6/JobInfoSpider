package com.pgb.spider.wechat.dao;

import com.pgb.spider.wechat.xom.request.Subscribe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscribeDao extends JpaRepository<Subscribe, Integer> {
}
