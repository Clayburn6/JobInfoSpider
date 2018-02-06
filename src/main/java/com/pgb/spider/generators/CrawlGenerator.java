package com.pgb.spider.generators;

import com.pgb.spider.executer.Task;

/**
 * @author Clayburn
 * @date : 2018/1/16 19:02
 * @description
 */
public interface CrawlGenerator<T> {
    T get(Task task);

    T Set(Task task);
}
