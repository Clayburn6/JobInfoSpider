package com.pgb.spider.web.service;

import com.pgb.spider.entity.JobItem;
import com.pgb.spider.web.dao.QueryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * @author Clayburn
 * @date : 2018/2/8 12:07
 * @description
 */
@Service
public class QueryServiceImpl implements QueryService {

    @Autowired
    private QueryDao queryDao;

    @Override
    public Page<JobItem> query(String title, String money, String company, Integer pageIndex, Integer pageSize) {
        return queryDao.getByTitleLikeAndCompanyLikeAndAndMoneyLikeAndExperienceLike("%%", "%%", "%%", "%%", new PageRequest(pageIndex, pageSize));
    }
}
