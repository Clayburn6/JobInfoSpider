package com.pgb.spider.config;

import com.pgb.spider.crawl.Crawl;
import com.pgb.spider.crawl.LagouCrawl;
import com.pgb.spider.generators.MapGenerator;
import com.pgb.spider.generators.StringGenerator;
import com.pgb.spider.http.client.HttpClient;
import com.pgb.spider.http.handler.DefaultTaskErrorHandler;
import com.pgb.spider.http.handler.ITaskErrorHandler;
import com.pgb.spider.store.IStore;
import com.pgb.spider.utils.SpiderUtils;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author Clayburn
 * @date : 2018/1/16 19:23
 * @description 爬虫配置类，主要配置以下值
 * 应用名 http客户端 结果处理类 代理 线程数 任务处理完毕线程操作（等待/结束） cookie httpheader 任务失败处理逻辑
 */
public class SpiderConfig {
    private Logger logger = Logger.getLogger(SpiderConfig.class);
    // 代理字符串
    private String proxys = null;
    // 使用多少个线程爬取，默认为1
    private int thread = 1;
    // 线程等待时间
    private int threadSleep = 0;
    // 使用的HttpClient默认为okclient
    private Class<? extends HttpClient> httpClient = Constants.HTTP_CLIENT;
    //private Boolean showHttpClientProgress = Constants.HTTP_SHOWHTTPCLIENTPROGRESS;
    // 存储，默认为打印
    private Class<? extends IStore> store = Constants.STORE;
    // 爬去策略
    private Class<? extends Crawl> crawl;
    // cookie
    private String cookie;
    // cookie启动器，默认为NOCookieGenerator
    private Class<? extends StringGenerator> cookieGenerator = Constants.COOKIDGENERATOR;
    // 请求头启动器，默认为NOHeaderGenerator
    private Class<? extends MapGenerator> headerGenerator = Constants.HEADERGENERATOR;
    // 请求头
    private Map<String, String> httpHeader;
    // 是否自动关闭
    private boolean autoClose = false;
    // 错误处理
    private Class<? extends ITaskErrorHandler> taskErrorHandler;

    /**
     * 如果找不到 log4j 的配置，就使用默认配置
     */
    static {
        Logger elogger = Logger.getLogger(SpiderConfig.class);
        if(!elogger.getParent().getAllAppenders().hasMoreElements()){
            Logger logger = Logger.getRootLogger();
            System.err.println("log4j.properties is not found , use default log4j config");
            logger.setLevel(Level.DEBUG);
            logger.addAppender(new ConsoleAppender(new PatternLayout("[%-5p][%-20d{yyyy/MM/dd HH:mm:ss}][%c] %m%n")));
        }
    }

    public String getProxys() {
        return proxys;
    }

    public SpiderConfig setProxys(String proxys) {
        SpiderUtils.addSystemPropertie(Constants.APP_PROXY_KEY,proxys);
        this.proxys = proxys;
        return this;
    }

    public boolean isAutoClose() {
        return autoClose;
    }

    public SpiderConfig setAutoClose(boolean autoClose) {
        SpiderUtils.addSystemPropertie(Constants.APP_AUTOCLOSE_KEY,autoClose);
        this.autoClose = autoClose;
        return this;
    }

    public int getThread() {
        return thread;
    }

    public SpiderConfig setThread(int thread) {
        SpiderUtils.addSystemPropertie(Constants.APP_THREAD_KEY,thread);
        this.thread = thread;
        return this;
    }

    public SpiderConfig setThread(int thread, int sleep) {
        SpiderUtils.addSystemPropertie(Constants.APP_THREAD_KEY,thread);
        SpiderUtils.addSystemPropertie(Constants.APP_THREAD_SLEEP_KEY,sleep);
        this.thread = thread;
        this.threadSleep = sleep;
        return this;
    }

    public int getThreadSleep() {
        return threadSleep;
    }

    public Class<? extends HttpClient> getHttpClient() {
        return httpClient;
    }

    public SpiderConfig setHttpClient(Class<? extends HttpClient> httpClient) {
        SpiderUtils.addSystemPropertie(Constants.APP_HTTPCLIENT_KEY,httpClient);
        this.httpClient = httpClient;
        return this;
    }

    public Class<? extends IStore> getStore() {
        return store;
    }

    public Class<? extends Crawl> getCrawl() {
        return LagouCrawl.class;
    }

    public SpiderConfig setStore(Class<? extends IStore> store) {
        SpiderUtils.addSystemPropertie(Constants.APP_STORE_KEY,store);
        this.store = store;
        return this;
    }

    public SpiderConfig setCookie(String cookie) {
        SpiderUtils.addSystemPropertie(Constants.APP_COOKIE_KEY,cookie);
        this.cookie = cookie;
        this.addHttpHeader("Cookie", cookie);
        return this;
    }

    public String getCookie() {
        return cookie;
    }

    public SpiderConfig addHttpHeader(String key, String value) {
        if(this.httpHeader == null){
            this.httpHeader = new HashMap<String, String>();
        }
        this.httpHeader.put(key, value);
        return this;
    }

    public Map<String, String> getHttpHeader() {
        return httpHeader;
    }

    public Class<? extends ITaskErrorHandler> getTaskErrorHandler() {
        return Optional.<Class>ofNullable(taskErrorHandler).orElse(DefaultTaskErrorHandler.class);
    }

    public SpiderConfig setTaskErrorHandler(Class<? extends ITaskErrorHandler> taskErrorHandler) {
        SpiderUtils.addSystemPropertie(Constants.APP_TASK_ERROR_KEY,taskErrorHandler);
        this.taskErrorHandler = taskErrorHandler;
        return this;
    }

    public Class<?> getCookieGenerator() {
        return cookieGenerator;
    }

    public SpiderConfig setCookieGenerator(Class<?> cookieGenerator) {
        this.cookieGenerator = (Class<? extends StringGenerator>) cookieGenerator;
        return this;
    }

    public Class<? extends MapGenerator> getHeaderGenerator() {
        return headerGenerator;
    }

    public SpiderConfig setHeaderGenerator(Class<? extends MapGenerator> headerGenerator) {
        this.headerGenerator = headerGenerator;
        return this;
    }

    public void print() {
        logger.info("---------------------------config--------------------------");
        logger.info("Proxys: "+this.getProxys());
        logger.info("Threads: "+this.getThread());
        logger.info("ThreadSleep: "+this.getThreadSleep());
        logger.info("HttpClient: "+this.getHttpClient());
        logger.info("Store: "+this.getStore());
        logger.info("Cookie: "+this.getCookie());
        logger.info("CookieGenerator: "+this.getCookieGenerator());
        logger.info("HttpHeaders: "+this.getHttpHeader());
        logger.info("HttpHeadersGenerator: "+this.getHeaderGenerator());
        logger.info("AutoClose: "+this.autoClose);
        logger.info("TaskErrorHandler: "+this.getTaskErrorHandler());
        logger.info("-------------------------------------------------------------");
    }
}
