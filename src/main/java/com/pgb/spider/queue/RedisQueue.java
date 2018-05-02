package com.pgb.spider.queue;

import com.pgb.spider.executer.Task;
import com.pgb.spider.redis.RedisTemplateFactory;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;

import java.security.MessageDigest;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author Clayburn
 * @date : 2018/5/2 14:12
 * @description
 */
public class RedisQueue implements CrawlQueue {

    private RedisTemplate<String, String> redisTemplate =
            (RedisTemplate<String, String>) RedisTemplateFactory.getRedisTemplate();

    // 返回一个，如果没有则返回空
    @Override
    public Task poll() throws Exception {
        Set<String> set = redisTemplate.opsForZSet().range(Thread.currentThread().getName(), 0, 1);
        if (set == null) {
            return null;
        }
        Iterator<String> iter = set.iterator();
        String signature = null;
        if (iter.hasNext()) {
            signature = iter.next();
            redisTemplate.opsForZSet().remove(Thread.currentThread().getName(), signature);
        }
        String url = redisTemplate.opsForValue().get(signature);
        return new Task(url);
    }

    // 返回一个，如果没有则阻塞
    @Override
    public Task take() throws Exception {
        Set<String> set = null;
        String signature = null;
        do {
            set = redisTemplate.opsForZSet().range(Thread.currentThread().getName(), 0, 1);
            if (set != null && set.size() != 0) {
                Iterator<String> iter = set.iterator();
                signature = iter.next();
                redisTemplate.opsForZSet().remove(Thread.currentThread().getName(), signature);
            }
        } while(set == null || set.size() == 0);
        String url = redisTemplate.opsForValue().get(signature);
        return new Task(url);
    }

    // 插入一个
    @Override
    public void push(Task task) throws Exception {
        if (task == null) {
            return;
        }
        String url = task.getUrl();
        if (StringUtils.isBlank(url)) {
            return;
        }
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
        byte[] digest = messageDigest.digest(url.getBytes());
        //将字节数组转成字符串
        String signature = byteToStr(digest);
        redisTemplate.opsForZSet().add(Thread.currentThread().getName()+"", signature, System.currentTimeMillis());
        redisTemplate.opsForValue().set(signature, url);
    }

    // 批量插入
    @Override
    public void pushAll(List<Task> tasks) throws Exception {
        if (tasks == null || tasks.isEmpty()) {
            return;
        }
        for (Task task : tasks) {
            push(task);
        }
    }

    @Override
    public void push(List<String> urls) throws Exception {

    }

    @Override
    public void clear() throws Exception {

    }

    @Override
    public void show() {

    }


    //将加密后的字节数组变成字符串
    private static String byteToStr(byte[] byteArray) {
        String strDigest = "";
        for (int i = 0; i < byteArray.length; i++) {
            strDigest += byteToHexStr(byteArray[i]);
        }
        return strDigest;
    }

    private static String byteToHexStr(byte mByte) {
        char[] Digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F'};
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];

        String s = new String(tempArr);
        return s;
    }
}
