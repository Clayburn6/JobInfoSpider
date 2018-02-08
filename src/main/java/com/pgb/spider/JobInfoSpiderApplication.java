package com.pgb.spider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author Clayburn
 * @date : 2018/2/8 11:59
 * @description
 */

@SpringBootApplication
public class JobInfoSpiderApplication {
    public static void main(String[] args) {
        SpringApplication.run(JobInfoSpiderApplication.class, args);
    }
}
