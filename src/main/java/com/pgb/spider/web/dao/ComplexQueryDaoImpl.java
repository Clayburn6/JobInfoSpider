package com.pgb.spider.web.dao;

import com.pgb.spider.entity.JobItem;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComplexQueryDaoImpl implements ComplexQueryDao {
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<JobItem> findJobItemList(String title, Integer money, String company, Integer pageIndex, Integer pageSize) {
        StringBuilder sb = new StringBuilder("");
        sb.append("from JobItem where 1 = 1 ");
        Map<String, Object> map = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(title)) {
            sb.append(" and title like :title ");
            map.put("title", "%" + title + "%");
        }
        if (money != null) {
            sb.append(" and minMoney > :money and maxMoney < :money ");
            map.put("money", money);
        }
        if (StringUtils.isNotBlank(company)) {
            sb.append(" and company like :company ");
            map.put("company", "%" + company + "%");
        }
        String hql = "select * " + sb.toString();
        String countHql = "select count(*) " + sb.toString();

        
        return null;
    }
}
