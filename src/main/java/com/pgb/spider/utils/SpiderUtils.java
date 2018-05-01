package com.pgb.spider.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * @author Clayburn
 * @date : 2018/1/16 18:33
 * @description
 */
public class SpiderUtils {
    private static final Logger log = LoggerFactory.getLogger(SpiderUtils.class);
    public static void addSystemPropertie(String key,Object value) {
        System.setProperty(key, value + "");
    }

    public static String exceptionMessage(int code,String message){
        return String.format("code:%d - message:%s",code,message);
    }

    public static SessionFactory sessionFactory = null;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration config = new Configuration().configure();

                    // 默认使用src文件夹下的hibernate.cfg.xml进行配置，若更改了路径，要附加上包路径如："/com/example/hibernate.cfg.xml"
            try {
                sessionFactory = config.buildSessionFactory();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return sessionFactory;
        } else {
            return  sessionFactory;
        }
    }
}
