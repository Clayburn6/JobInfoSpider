package com.pgb.spider;

import com.pgb.spider.config.SpiderConfig;
import com.pgb.spider.executer.TaskExecuter;
import com.pgb.spider.generators.MapGenerator;
import com.pgb.spider.generators.StringGenerator;
import com.pgb.spider.http.HttpProxy;
import com.pgb.spider.http.client.HttpClient;
import com.pgb.spider.http.client.HttpClientProxy;
import com.pgb.spider.queue.CrawlQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Clayburn
 * @date : 2018/1/16 19:36
 * @description
 */
public class JobInfoSpiderContext {
    private Logger logger = LoggerFactory.getLogger(JobInfoSpiderContext.class);
    private SpiderConfig config;
    private int thread;
    private HttpProxy proxy = null;
    // 线程池
    private ExecutorService service = Executors.newCachedThreadPool();
    private boolean started = false;

    public JobInfoSpiderContext(final SpiderConfig config) {
        this.config = config;
    }

    /**
     * 启动爬虫程序
     * 只能启动一次，启动之前先判断之前有没有启动过
     * @param queue
     */
    public void start(CrawlQueue queue) throws Exception {
        if(!started){
            logger.info("starting...");
            config.print();
            this.thread = config.getThread();
            for (int i = 0; i < thread; i++) {
                TaskExecuter executer = new TaskExecuter(queue, this.bulidHttpClient(), this.config.getStore().newInstance(), this.config.getCrawl().newInstance(), this.config.getTaskErrorHandler().newInstance(), this.config.getThreadSleep(), this.config.isAutoClose());
                logger.info("new thread:" + executer.getId());
                service.execute(executer);
            }
            /**
             * 不可以再继续 提交新的任务 已经提交的任务不影响
             */
            service.shutdown();
            this.started = true;
            logger.info("start success");
        }else{
            logger.warn("the spider has already started");
        }
    }

    private HttpClient bulidHttpClient() throws Exception {
        logger.info("bulid httpclient");
        if(this.config.getProxys() != null && this.proxy ==null){
            this.proxy = new HttpProxy(this.config.getProxys());
        }
        HttpClient client  = this.config.getHttpClient().newInstance();

        StringGenerator cookieGenerator = null;
        if (this.config.getCookieGenerator() != null) {
            cookieGenerator = (StringGenerator) this.config.getCookieGenerator().newInstance();
        }

        MapGenerator headerGenerator = null;
        if (this.config.getHeaderGenerator() != null) {
            headerGenerator = (MapGenerator) this.config.getHeaderGenerator().newInstance();
        }

        return new HttpClientProxy(client)
                .setCookieGenerator(cookieGenerator)
                .setHeaderGenerator(headerGenerator)
                .setProxy(this.proxy)
                .setCookie(this.config.getCookie())
                .setHttpHeader(this.config.getHttpHeader());
    }
}
