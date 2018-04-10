package com.pgb.spider.web.dao;

import com.pgb.spider.entity.JobItem;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;


@Repository
public class ComplexQueryDaoImpl implements ComplexQueryDao {
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<JobItem> findJobItemList(String title, Integer money, String company, Integer pageIndex, Integer pageSize) {

        StringBuilder sql = new StringBuilder("select * from job_info where 1 = 1 ");

        if (StringUtils.isNotBlank(title)) {
            sql.append(" and title like '%" + title + "%'");
        }
        if (money != null) {
            sql.append(" and min_money <= " + (money / 1000) + " and max_money >= " + (money / 1000));
        }
        if (StringUtils.isNotBlank(company)) {
            sql.append(" and company like '%" + company + "%'");
        }

        sql.append(" limit " + (pageIndex - 1) + ", " + pageSize);

        Query query = entityManager.createNativeQuery(sql.toString(), JobItem.class);
        return query.getResultList();
    }

    @Override
    public Integer countJobItem(String title, Integer money, String company) {
        StringBuilder sql = new StringBuilder("select count(*) from job_info where 1 = 1 ");

        if (StringUtils.isNotBlank(title)) {
            sql.append(" and title like '%" + title + "%'");
        }
        if (money != null) {
            sql.append(" and min_money <= " + (money / 1000) + " and max_money >= " + (money / 1000));
        }
        if (StringUtils.isNotBlank(company)) {
            sql.append(" and company like '%" + company + "%'");
        }

        Query query = entityManager.createNativeQuery(sql.toString());

        return ((BigInteger)query.getSingleResult()).intValue();
    }
}
