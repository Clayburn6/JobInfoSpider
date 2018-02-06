package com.pgb.spider.executer;

import com.pgb.spider.executer.response.TaskErrorResponse;
import com.pgb.spider.executer.response.TaskResponse;
import com.pgb.spider.http.client.HttpClient;
import com.pgb.spider.http.handler.ITaskErrorHandler;
import com.pgb.spider.queue.CrawlQueue;
import com.pgb.spider.store.IStore;
import com.pgb.spider.utils.NameUtils;
import okhttp3.Cookie;
import okhttp3.HttpUrl;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Clayburn
 * @date : 2018/1/16 18:16
 * @description 任务执行器，主要工作是从队列中取出任务然后执行任务
 */
public class TaskExecuter implements Runnable {
    // 错误处理
    private final ITaskErrorHandler errorHandler;
    private Logger logger = LoggerFactory.getLogger(TaskExecuter.class);
    // url队列
    private CrawlQueue queue;

    private HttpClient httpClient;
    private IStore store;
    private String id;
    private boolean autoClose;
    private int sleep;

    public TaskExecuter(CrawlQueue queue, HttpClient httpClient, IStore store, ITaskErrorHandler errorHandler, int sleep, boolean autoClose) {
        this.queue = queue;
        this.httpClient = httpClient;
        this.store = store;
        this.id = NameUtils.name(TaskExecuter.class);
        this.errorHandler = errorHandler;
        this.autoClose = autoClose;
        this.sleep = sleep;
    }

    @Override
    public void run() {
        boolean flag = true;
        // 使用带标签的while循环
        loop:while (flag) {
            try {
                Task task = null;
                if(autoClose){
                    task = this.queue.poll();
                    if(task == null){
                        flag = false;
                        break loop;
                    }
                }else{
                    task = this.queue.take();
                }
                TimeUnit.MILLISECONDS.sleep(sleep);
                logger.info(this.getId()+" GET - "+task);
                TaskResponse response = this.httpClient.proxy().doGet(task);
                response.setQueue(this.queue);
                List<String> cookies = response.getResponse().headers("Set-Cookie");
                Arrays.toString(cookies.toArray());
                Cookie cookie = Cookie.parse(HttpUrl.get(URI.create(task.getUrl())), "");
                if(response.isEmpty()){
                    this.errorHandler.error(new TaskErrorResponse(response));
                }else{
                    if (task.getUrl().matches("http(s)?://www.lagou.com(/)?")) {
                        // 第一步 将首页所有的 <https://www.lagou.com/zhaopin/*>的url添加到队列
                        addTask(response, 1);
                    } else if (task.getUrl().matches("https://www.lagou.com/zhaopin/[^0-9]*(/.*)+")) {
                        // 第二步 找到该网页下所有的<https://www.lagou.com/jobs/\\d+.html>
                        addTask(response, 2);
                    } else if (task.getUrl().matches("https://www.lagou.com/jobs/(\\d)+.html")) {
                        // 第三步解析目标网页
                        this.store.store(response);
                    }
                }
                response.getResponse().close();
            } catch (Exception e) {
                e.printStackTrace();
                logger.error(this.getId()+" - "+ e.getLocalizedMessage());
            }
        }
        logger.info(id+" : over");
    }

    public String getId() {
        return id;
    }

    /**
     * 根据正则表达式将response中url添加到队列
     * @param response
     * @param step 第几步
     */
    private void addTask(TaskResponse response, int step) throws Exception {
        Document doc = null;
        try {
            doc = response.getDocument();
        } catch (IOException e) {
            logger.info("无法解析该url");
            return;
        }
        response.select("ul.item_con_list")
                .select("li")
                .select(".p_top")
                .select("a").stream()
                .map(element -> element.attr("href"))
                .forEach(elem -> {
                    try {
                        response.getQueue().push(new Task(elem,"jobs.lagou.items"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
//        Elements links = doc.select("a[href]");
//        for (Element link : links) {
//            try {
//                String url = link.attr("abs:href");
//                if (step == 1) {
//                    if (url.matches("https://www.lagou.com/zhaopin/[^0-9]*")) {
//                        for (int i = 1; i <= 30; i++) {
//                            if (url.endsWith("/")) {
//                                this.queue.push(new Task(link.attr("abs:href") + i + "/?filterOption=" + i));
//                            } else {
//                                this.queue.push(new Task(link.attr("abs:href") + "/" + i + "/?filterOption=" + i));
//                            }
//                        }
//                    }
//                } else if (step == 2) {
//                    response.select("ul.item_con_list")
//                            .select("li")
//                            .select(".p_top")
//                            .select("a").stream()
//                            .map(element -> element.attr("href"))
//                            .forEach(elem -> {
//                                try {
//                                    response.getQueue().push(new Task(elem,"jobs.lagou.items"));
//                                } catch (Exception e) {
//                                    e.printStackTrace();
//                                }
//                            });
//                }
//            } catch (Exception e) {
//            }
//        }
    }
}
