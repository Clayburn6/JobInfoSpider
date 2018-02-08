package com.pgb.spider.crawl;

import com.pgb.spider.executer.Task;
import com.pgb.spider.executer.response.TaskResponse;
import com.pgb.spider.queue.CrawlQueue;
import com.pgb.spider.store.IStore;

/**
 * @author Clayburn
 * @date : 2018/2/6 20:50
 * @description
 */
public interface Crawl {
    void crawl(CrawlQueue queue, Task task, TaskResponse response, IStore iStore);
}
