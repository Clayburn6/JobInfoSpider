package com.pgb.spider.wechat.dao;

import com.pgb.spider.wechat.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoDao extends JpaRepository<UserInfo, Integer> {
    /**
     * 根据微信号和openid查找用户
     * @param wechatCode 微信号
     * @param openid openid
     * @return
     */
    UserInfo findByWechatCodeAndOpenid(String wechatCode, String openid);
}
