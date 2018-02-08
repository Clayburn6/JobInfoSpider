package com.pgb.spider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

/**
 * @author Clayburn
 * @date : 2018/2/8 11:59
 * @description
 */

@EnableAutoConfiguration
public class JobInfoSpiderApplication {
    public static void main(String[] args) {
        SpringApplication.run(JobInfoSpiderApplication.class, args);
    }
}
