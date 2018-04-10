package com.pgb.spider.web.dao;

import com.pgb.spider.entity.JobItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Clayburn
 * @date : 2018/2/8 12:08
 * @description
 */
public interface QueryDao extends JpaRepository<JobItem, Integer> {
    Page<JobItem> getByTitleLikeAndCompanyLikeAndAndMoneyLike(String title,
                                                              String company, String money, Pageable pageable);

    JobItem getById(Integer id);
}
