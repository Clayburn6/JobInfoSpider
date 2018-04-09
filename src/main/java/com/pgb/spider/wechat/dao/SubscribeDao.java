package com.pgb.spider.wechat.dao;

import com.pgb.spider.wechat.xom.request.Subscribe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscribeDao extends JpaRepository<Subscribe, Integer> {
    /**
     * 根据微信号和openid查找用户
     * @param toUserName 微信号
     * @param fromUserName openid
     * @return
     */
    Subscribe findByToUserNameAndFromUserName(String toUserName, String fromUserName);
}
