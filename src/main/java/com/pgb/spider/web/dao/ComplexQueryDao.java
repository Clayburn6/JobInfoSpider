package com.pgb.spider.web.dao;

import com.pgb.spider.entity.JobItem;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

public interface ComplexQueryDao {
    /**
     * 条件查询职位信息
     * @param title
     * @param money
     * @param company
     * @param pageIndex
     * @param pageSize
     * @return
     */
    List<JobItem> findJobItemList(String title, Integer money, String company, Integer pageIndex, Integer pageSize);


    Integer countJobItem(String title, Integer money, String company);
}
