package com.pgb.spider.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Clayburn
 * @date : 2018/5/2 2:48
 * @description
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class RedisTest {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    @Test
    public void addKeyValue() {
        redisTemplate.opsForValue().set("name", "penggb");
    }
}
