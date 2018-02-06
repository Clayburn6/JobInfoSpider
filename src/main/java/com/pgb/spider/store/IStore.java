package com.pgb.spider.store;

import com.pgb.spider.executer.response.TaskResponse;

/**
 * @author Clayburn
 * @date : 2018/1/16 18:47
 * @description
 */
public interface IStore {
    void store(TaskResponse response) throws Exception;
}
