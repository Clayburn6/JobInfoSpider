package com.pgb.spider.web.service;

import com.pgb.spider.entity.JobItem;
import com.pgb.spider.web.dao.ComplexQueryDao;
import com.pgb.spider.web.dao.QueryDao;
import com.pgb.spider.web.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

/**
 * @author Clayburn
 * @date : 2018/2/8 12:07
 * @description
 */
@Service
public class QueryServiceImpl implements QueryService {

    @Autowired
    private QueryDao queryDao;

    @Autowired
    private ComplexQueryDao complexQueryDao;

    @Override
    public Pagination<JobItem> query(String title, Integer money, String company, Integer pageIndex, Integer pageSize) {
        List<JobItem> jobItemList = complexQueryDao.findJobItemList(title, money, company, pageIndex, pageSize);
        Integer count = complexQueryDao.countJobItem(title, money, company);

        Pagination<JobItem> result = new Pagination<JobItem>(pageIndex, count % pageSize == 0 ? count / pageSize : count / pageSize + 1, pageSize, jobItemList);

        return result;
    }

    @Override
    public JobItem queryDetail(Integer id) {
        return queryDao.getById(id);
    }
}
