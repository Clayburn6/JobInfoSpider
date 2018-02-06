package com.pgb.spider.executer.response;

import com.pgb.spider.executer.Task;
import com.pgb.spider.queue.CrawlQueue;

import java.io.IOException;

/**
 * @author Clayburn
 * @date : 2018/1/16 18:23
 * @description
 */
public interface SpiderResponse {
    String getContent() throws IOException;
    Task getTask();
    boolean isGroup(String group);
    boolean isGroupStartWith(String groupPrefix);
    boolean isGroupEndWith(String end);
    boolean isGroupContains(String str);
    CrawlQueue getQueue();
}
