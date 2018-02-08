package com.pgb.spider.web.service;

import com.pgb.spider.entity.JobItem;
import org.springframework.data.domain.Page;

/**
 * @author Clayburn
 * @date : 2018/2/8 12:07
 * @description
 */
public interface QueryService {
    Page<JobItem> query();
}
