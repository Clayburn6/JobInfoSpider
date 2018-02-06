package com.pgb.spider.http.handler;

import com.pgb.spider.executer.response.TaskErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author Clayburn
 * @date : 2018/1/16 19:27
 * @description
 */
public class DefaultTaskErrorHandler implements ITaskErrorHandler {
    private Logger logger = LoggerFactory.getLogger(DefaultTaskErrorHandler.class);

    @Override
    public void error(TaskErrorResponse response) {
        try {
            logger.info("task error: "+ response.getContent());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
