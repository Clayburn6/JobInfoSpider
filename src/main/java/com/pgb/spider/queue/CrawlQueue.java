package com.pgb.spider.queue;

import com.pgb.spider.executer.Task;

import java.util.List;

/**
 * @author Clayburn
 * @date : 2018/1/16 18:26
 * @description 爬取队列接口
 */
public interface CrawlQueue {
    /**
     * 如果队列为空 结果为 null
     * @return
     * @throws Exception
     */
    Task poll() throws Exception;

    /**
     * 如果队列为空 阻塞等待 直到队列不为空
     * @return
     * @throws Exception
     */
    Task take() throws Exception;

    /**
     * 入队
     * @param task
     * @throws Exception
     */
    void push(Task task) throws Exception;

    /**
     * 批量入队
     * @param tasks
     * @throws Exception
     */
    void pushAll(List<Task> tasks) throws Exception;

    /**
     * 批量入队
     * @param urls
     * @throws Exception
     */
    void push(List<String> urls) throws Exception;

    /**
     * 清空队列
     * @throws Exception
     */
    void clear() throws Exception;

    /**
     * 打印队列里面所有的url
     */
    void show();
}
