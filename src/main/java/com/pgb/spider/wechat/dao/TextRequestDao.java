package com.pgb.spider.wechat.dao;

import com.pgb.spider.wechat.xom.request.TextRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TextRequestDao extends JpaRepository<TextRequest, Integer> {
}
