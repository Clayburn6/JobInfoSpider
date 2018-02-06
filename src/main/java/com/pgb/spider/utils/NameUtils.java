package com.pgb.spider.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Clayburn
 * @date : 2018/1/16 18:15
 * @description 名称生成类 根据递增序列规则
 */
public class NameUtils {
    private static Logger logger = LoggerFactory.getLogger(NameUtils.class);
    private static Map<String, Integer> nameMap = new HashMap<>();
    private static Map<String, String> idMap = new HashMap<>();

    public synchronized static String name(Class clazz){
        String name = clazz.getSimpleName();
        Integer index = nameMap.getOrDefault(name, 1);
        nameMap.put(name, index + 1);
        return bulidName(name,index);
    }

    public synchronized static String id(Class clazz){
        String name = clazz.getSimpleName();
        return idMap.getOrDefault(name, UUID.randomUUID().toString());
    }

    private static String bulidName(String name, Integer index) {
        return name + "-" + index;
    }

    public static void main(String[] args) {
//        logger.info(NameUtils.name(TaskExecuter.class));
//        System.out.println(NameUtils.name(TaskExecuter.class));
//        System.out.println(NameUtils.name(TaskExecuter.class));
//        System.out.println(NameUtils.name(TaskExecuter.class));
    }
}
