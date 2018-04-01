package com.pgb.spider.web.service;

import com.pgb.spider.entity.JobItem;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @author Clayburn
 * @date : 2018/2/8 13:56
 * @description
 */
@SpringBootTest//(classes = {QueryServiceImpl.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class QueryServiceImplTest {
    @Autowired
    private QueryService queryService;

    @Test
    public void query() throws Exception {
//        Page<JobItem> page = queryService.query();
//
//        Assert.assertTrue(page != null);
    }

}