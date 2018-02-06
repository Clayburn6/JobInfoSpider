package com.pgb.spider.queue;


import com.pgb.spider.executer.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author Clayburn
 * @date : 2018/1/16 18:27
 * @description
 */
public class TaskQueue implements CrawlQueue {

    private Logger logger = LoggerFactory.getLogger(TaskQueue.class);

    private BlockingQueue<Task> queue;
    private BlockingQueue<Task> faildQueue;
    private Map<Task,Integer> faildCounter;

    private static TaskQueue taskQueue;

    public static TaskQueue of(){
        return TaskQueue.of(Integer.MAX_VALUE);
    }

    public static TaskQueue of(int calacity){
        return new TaskQueue(calacity);
    }

    private TaskQueue(int calacity) {
        this.queue = new LinkedBlockingDeque<Task>(calacity);
        this.faildQueue = new LinkedBlockingDeque<Task>();
        this.faildCounter = new HashMap<Task, Integer>();
        logger.info("create queue whith calacity " + calacity);
    }

    @Override
    public Task poll() throws InterruptedException {
        if(this.queue.isEmpty()){
            logger.info(Thread.currentThread().getName() + " queue is empty");
        }
        Task task = this.queue.poll();
        logger.info(Thread.currentThread().getName() + " pull task " + task);
        return task;
    }

    @Override
    public Task take() throws InterruptedException {
        if(this.queue.isEmpty()){
            logger.info(Thread.currentThread().getName() + " queue is empty");
        }
        Task task = this.queue.take();
        logger.info(Thread.currentThread().getName() + " take task " + task);
        return task;
    }

    @Override
    public void push(Task task) throws InterruptedException {
        this.queue.put(task);
        logger.info(Thread.currentThread().getName() + " push task " + task);
    }

    @Override
    public void pushAll(List<Task> tasks) throws InterruptedException {
        for (Task task : tasks) {
            this.queue.put(task);
        }
        logger.info(Thread.currentThread().getName() + " put task list " + tasks.size());
    }

    @Override
    public void push(List<String> urls) {
        urls.stream().map(url -> new Task(url)).forEach(task -> {
            try {
                this.queue.put(task);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        logger.info(Thread.currentThread().getName() + " put urls list " + urls.size());
    }

    @Override
    public void clear(){
        this.queue.clear();
        logger.info(Thread.currentThread().getName() + " clear queue");
    }

    @Override
    public void show() {
        // 设置日志级别为error，便于查看
        for (Task task : queue) {
            logger.error(task.getUrl());
        }
        logger.error("\n\n");
    }
}
