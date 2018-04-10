package com.pgb.spider.web.service;

import com.pgb.spider.entity.JobItem;
import com.pgb.spider.web.utils.Pagination;
import org.springframework.data.domain.Page;

/**
 * @author Clayburn
 * @date : 2018/2/8 12:07
 * @description
 */
public interface QueryService {
    Pagination<JobItem> query(String title, Integer money, String company, Integer pageIndex, Integer pageSize);

    JobItem queryDetail(Integer id);
}
