package com.pgb.spider.schedulejob;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Clayburn
 * @date : 2018/5/2 16:08
 * @description
 */
@Component
public class PushJobTask {

    private static final Logger log = LoggerFactory.getLogger(PushJobTask.class);

    @Scheduled(fixedRate = 500000)
    public void pushJobTask() {
        log.info("ssssssssssss");
    }
}
