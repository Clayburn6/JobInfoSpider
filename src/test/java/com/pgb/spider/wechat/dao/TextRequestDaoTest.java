package com.pgb.spider.wechat.dao;

import com.pgb.spider.wechat.xom.request.TextRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TextRequestDaoTest {

    @Autowired
    private TextRequestDao textRequestDao;

    @Test
    public void test() {

        TextRequest textRequest = new TextRequest();
        textRequest.setContent("345");

        textRequestDao.save(textRequest);
    }

}