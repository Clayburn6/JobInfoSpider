package com.pgb.spider.web.dao;

import com.pgb.spider.entity.JobItem;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ComplexQueryDaoImplTest {

    @Autowired
    private ComplexQueryDao complexQueryDao;

    @Autowired
    private QueryDao queryDao;

    @Test
    public void findJobItemList() throws Exception {
        List<JobItem> list = complexQueryDao.findJobItemList("Java开发工程师", 8000, "", 1, 2);

        Assert.assertNotNull(list);
    }

    @Test
    public void aa() {
        JobItem jobItem = queryDao.getById(899);

        Assert.assertNotNull(jobItem);
    }

    @Test
    public void bb() {
        Integer i = complexQueryDao.countJobItem("Java开发工程师", 8000, "");

        Assert.assertNotNull(i);
    }

}