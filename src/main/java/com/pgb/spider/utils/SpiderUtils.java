package com.pgb.spider.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * @author Clayburn
 * @date : 2018/1/16 18:33
 * @description
 */
public class SpiderUtils {
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
            }
            return sessionFactory;
        } else {
            return  sessionFactory;
        }
    }
}
