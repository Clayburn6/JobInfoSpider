package com.pgb.spider.generators;

import com.pgb.spider.executer.Task;

import java.util.UUID;

import static org.junit.Assert.*;

/**
 * @author Clayburn
 * @date : 2018/1/16 19:15
 * @description
 */
public class CookieGeneratorTest implements StringGenerator{

    @Override
    public String get(Task task) {
        String cookie = "v="+ UUID.randomUUID().toString();
        System.out.println(cookie);
        return cookie;
    }

    @Override
    public String Set(Task task) {
        return null;
    }
}