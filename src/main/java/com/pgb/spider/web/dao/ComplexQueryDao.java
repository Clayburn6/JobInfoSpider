package com.pgb.spider.web.dao;

import com.pgb.spider.entity.JobItem;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
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
    public List<JobItem> findJobItemList(String title, Integer money, String company, Integer pageIndex, Integer pageSize);
}
