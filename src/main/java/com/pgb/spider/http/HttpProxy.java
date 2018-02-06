package com.pgb.spider.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author Clayburn
 * @date : 2018/1/16 17:34
 * @description HTTP代理
 */
public class HttpProxy {
    private Logger logger = LoggerFactory.getLogger(HttpProxy.class);
    private Map<String, Integer> proxys;
    private Random random = new Random();

    public HttpProxy(String proxys) {
        this.proxys = new HashMap<String, Integer>();
        /*
        使用Java8新特性操作集合
        Arrays.stream(proxys.split(","))使用","分割字符串并转成一个Stream集合
        Arrays.stream(proxys.split(",")).map(item -> item.split(":"))Stream元素原来是字符串，现在被转成字符串数组
        紧接着遍历每个字符串数组元素，字符串数组中第儿个是ip
         */
        Arrays.stream(proxys.split(",")).map(item -> item.split(":")).forEach(item -> {
            Integer ip = null;
            if(item.length > 1){
                ip = Integer.parseInt(item[1]);
            }
            this.proxys.put(item[0], ip);
        });
    }

    /**
     * 随机获取一个代理
     * @return
     */
    public ProxyTuple randomProxy(){
        synchronized (this.proxys){
            if (this.proxys.size() == 0) {
                logger.info("代理全部失效");
            }
            Map.Entry<String,Integer> entity = this.proxys.entrySet().stream().collect(Collectors.toList()).get(random.nextInt(proxys.size()));
            return new ProxyTuple(entity.getKey(), entity.getValue());
        }
    }

    /**
     * 如果代理失效，从代理池中删除代理
     * @param proxy
     */
    public void disable(ProxyTuple proxy){
        synchronized (this.proxys) {
            logger.info("disable-" + proxy);
            this.proxys.remove(proxy.ip());
        }
    }

    public boolean isEmpty(){
        return this.proxys.isEmpty();
    }
}
