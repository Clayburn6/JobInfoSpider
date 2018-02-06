package com.pgb.spider.executer.response;

import com.pgb.spider.executer.Task;
import com.pgb.spider.queue.CrawlQueue;

import java.io.IOException;

/**
 * @author Clayburn
 * @date : 2018/1/16 18:45
 * @description
 */
public class TaskErrorResponse implements SpiderResponse {
    private Task task;
    private String message;
    private CrawlQueue queue;
    private String charset;

    public TaskErrorResponse(TaskResponse response) {
        this.task =response.getTask();
        this.queue = response.getQueue();
        this.message = response.getMessage();
    }

    @Override
    public String getContent() throws IOException {
        return message;
    }

    @Override
    public Task getTask() {
        return task;
    }

    @Override
    public boolean isGroup(String group) {
        return false;
    }

    @Override
    public boolean isGroupStartWith(String groupPrefix) {
        return task.getGroup().startsWith(groupPrefix);
    }

    @Override
    public boolean isGroupEndWith(String end) {
        return task.getGroup().endsWith(end);
    }

    @Override
    public boolean isGroupContains(String str) {
        return task.getGroup().contains(str);
    }

    @Override
    public CrawlQueue getQueue() {
        return queue;
    }
}
