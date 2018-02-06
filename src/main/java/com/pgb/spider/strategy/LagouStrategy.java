package com.pgb.spider.strategy;

/**
 * @author Clayburn
 * @date : 2018/1/23 14:43
 * @description 拉勾网的爬取策略
 * 第一步：将首页所有的 <https://www.lagou.com/zhaopin/*>的url添加到队列
 * 第二步：访问<https://www.lagou.com/zhaopin/*>，重新组合类似https://www.lagou.com/zhaopin/Java/3/?filterOption=3
 * 第三步：访问第二步的url，得到目标网页的url
 */
public class LagouStrategy implements CrawlStrategy {

    @Override
    public void run() {

    }
}
