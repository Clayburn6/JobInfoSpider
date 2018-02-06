package com.pgb.spider.store;

import com.pgb.spider.executer.response.TaskResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Clayburn
 * @date : 2018/1/16 18:48
 * @description
 */
public class DescribeStore implements IStore {
    private Logger logger = LoggerFactory.getLogger(DescribeStore.class);
    @Override
    public void store(TaskResponse response) throws Exception {
        logger.info("==================desc=================");
        logger.info(String.format("task id: %s",response.getTask().getId()));
        logger.info(String.format("thread name: %s",Thread.currentThread().getName()));
        logger.info(String.format("bytes: %d",response.getContentBytes().length));
        logger.info("=======================================");
    }
}
