package com.pgb.spider;

import com.pgb.spider.config.SpiderConfig;
import com.pgb.spider.executer.Task;
import com.pgb.spider.http.client.okhttp.OkHttpClient;
import com.pgb.spider.queue.TaskQueue;
import com.pgb.spider.store.LagouStore;

/**
 * @author Clayburn
 * @date : 2018/1/16 17:31
 * @description
 */
public class JobInfoSpiderApplication {
    public static void main(String[] args) throws Exception {
        String cockie = "";

        SpiderConfig config = new SpiderConfig()
                .setThread(5)
                .setHttpClient(OkHttpClient.class)
                .setCookie(cockie)
                .setAutoClose(true)
                .addHttpHeader("Host","www.lagou.com")
                .addHttpHeader("Upgrade-Insecure-Requests","1")
                .addHttpHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
                .setStore(LagouStore.class);
        JobInfoSpiderContext context = new JobInfoSpiderContext(config);
        TaskQueue queue = TaskQueue.of();
        for (int i = 1; i <= 30; i++) {
            queue.push(new Task("https://www.lagou.com/zhaopin/Java/" + i + "/?filterOption=" + i));
        }
//        queue.push(new Task("https://www.lagou.com/zhaopin/Java/" + 1 + "/?filterOption=" + 1));
//        queue.push(new Task("https://www.zhihu.com/people/wmhsr/activities"));
//        queue.push(new Task("https://www.zhihu.com/api/v4/members/excited-vczh/followees?offset=0&limit=20"));
        context.start(queue);
    }
}
