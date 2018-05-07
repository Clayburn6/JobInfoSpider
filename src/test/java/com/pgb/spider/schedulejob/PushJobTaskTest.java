package com.pgb.spider.schedulejob;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @author Clayburn
 * @date : 2018/5/3 11:22
 * @description
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class PushJobTaskTest {
    @Autowired
    private PushJobTask pushJobTask;

    @Test
    public void pushJobTask() throws Exception {
        pushJobTask.pushJobTask();
    }

}