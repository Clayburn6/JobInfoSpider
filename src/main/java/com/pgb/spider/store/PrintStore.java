package com.pgb.spider.store;

import com.pgb.spider.executer.response.TaskResponse;

import java.io.IOException;

/**
 * @author Clayburn
 * @date : 2018/1/16 18:49
 * @description
 */
public class PrintStore implements IStore {
    @Override
    public void store(TaskResponse response) throws IOException {
        System.out.println(response.getContent());
    }
}
