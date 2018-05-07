package com.pgb.spider.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author Clayburn
 * @date : 2018/5/2 14:15
 * @description
 */
public class RedisTemplateFactory {
    private RedisTemplateFactory(){}

    private static final Logger logger = LoggerFactory.getLogger(RedisConfig.class);

    private static RedisTemplate<?, ?> redisTemplate = null;

    public  static synchronized RedisTemplate<?, ?> getRedisTemplate(){
        if (redisTemplate == null) {
            JedisPoolConfig config = new JedisPoolConfig();

            JedisConnectionFactory factory = new JedisConnectionFactory();
            factory.setPoolConfig(config);
            factory.setHostName("120.79.133.133");
            factory.setPort(6379);
            factory.setPassword("penggb023");
            factory.setTimeout(0);
            factory.afterPropertiesSet();
            logger.info("JedisConnectionFactory bean init success.");

            redisTemplate = new StringRedisTemplate(factory);
        }

        return redisTemplate;
    }

    public static void main(String[] args) {
        RedisTemplate<String, String> redisTemplate = (RedisTemplate<String, String>) getRedisTemplate();

        redisTemplate.opsForValue().set("hello", "你好啊！");
    }
}
