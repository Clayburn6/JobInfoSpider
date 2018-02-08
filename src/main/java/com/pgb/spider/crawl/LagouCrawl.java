package com.pgb.spider.crawl;

import com.pgb.spider.executer.Task;
import com.pgb.spider.executer.response.TaskResponse;
import com.pgb.spider.queue.CrawlQueue;
import com.pgb.spider.store.IStore;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Clayburn
 * @date : 2018/2/6 20:49
 * @description 对拉钩网的爬去策略
 */
public class LagouCrawl implements Crawl {
    private Logger logger = LoggerFactory.getLogger(LagouCrawl.class);
    private static Set<Task> urlSet = new HashSet<Task>();
    @Override
    public void crawl(CrawlQueue queue, Task task, TaskResponse response, IStore iStore) {
        if (task != null && response != null && queue != null) {
            String url = task.getUrl();
            Document doc = null;
            try {
                doc = response.getDocument();
            } catch (IOException e) {
                logger.info("无法解析该url");
                return;
            }
            Elements links = doc.select("a[href]");
            urlSet.clear();
            if (url.matches("http(s)?://www.lagou.com(/)?")) {
                for (Element link : links) {
                    try {
                        String tempUrl = link.attr("abs:href");
                        if (tempUrl.matches("https://www.lagou.com/zhaopin/[^0-9]*")) {
                            for (int i = 1; i <= 30; i++) {
                                if (url.endsWith("/")) {
                                    urlSet.add(new Task(tempUrl + i + "/?filterOption=" + i));
                                } else {
                                    urlSet.add(new Task(tempUrl + "/" + i + "/?filterOption=" + i));
                                }
                            }
                        }
                    } catch (Exception e) {
                        logger.error("jsoup 解析出错！");
                    }
                }

            } else if (url.matches("https://www.lagou.com/zhaopin/.*filterOption=.*")) {
                doc.select("ul.item_con_list")
                        .select("li")
                        .select(".p_top")
                        .select("a").stream()
                        .map(element -> element.attr("href"))
                        .forEach(elem -> {
                            try {
                                urlSet.add(new Task(elem,"jobs.lagou.items"));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });
            } else if (url.matches("https://www.lagou.com/jobs/[0-9]+.html")) {
                try {
                    iStore.store(response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            try {
                queue.pushAll(Arrays.asList(urlSet.toArray(new Task[urlSet.size()])));
            } catch (Exception e) {
                logger.error("新任务加入队列出错");
            }

        }
    }
}
