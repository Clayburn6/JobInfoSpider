package com.pgb.spider.wechat.dao;

import com.pgb.spider.wechat.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoDao extends JpaRepository<UserInfo, Integer> {
}
