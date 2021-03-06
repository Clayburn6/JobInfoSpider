package com.pgb.spider.http.client;

import com.pgb.spider.executer.Task;
import com.pgb.spider.executer.response.TaskResponse;
import com.pgb.spider.generators.MapGenerator;
import com.pgb.spider.generators.CookieGenerator;
import com.pgb.spider.generators.NoHeaderGenerator;
import com.pgb.spider.generators.StringGenerator;
import com.pgb.spider.http.HttpProxy;
import com.pgb.spider.http.ProxyTuple;
import com.pgb.spider.http.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @author Clayburn
 * @date : 2018/1/16 18:56
 * @description
 */
public class HttpClientProxy implements HttpClient {

    private Logger logger = LoggerFactory.getLogger(HttpClientProxy.class);
    private HttpClient client;
    private HttpProxy proxy;
    private StringGenerator cookieGenerator;
    private MapGenerator headerGenerator;

    public HttpClientProxy(HttpClient client) {
        this.client = client;
    }

    @Override
    public HttpClient setProxy(HttpProxy proxy) {
        this.proxy = proxy;
        try {
            this.client.setProxy(proxy);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return this;
    }

    @Override
    public TaskResponse doGet(Task task) {
        this.makeGenerators(task);
        String message = "";
        try {
            return this.client.doGet(task);
        } catch (Exception e) {
            if (e instanceof HttpException) {
                if(e instanceof Http40XException){
                    message = e.getMessage();
                }else if(e instanceof Http50XException){
                    message = e.getMessage();
                }else if(e instanceof Http30XException){
                    message = "resources redirect:" + e.getMessage();
                }
            } else {
                if (this.proxy != null) {
                    this.proxy.disable(this.getCurrentProxyTuple());
                }
                message = e.getMessage();
            }
            logger.error(task + " - " + message);
        }
        return TaskResponse.empty().setTask(task).setMessage(message);
    }

    /**
     * 如果配置了生成器，则在请求之前调用生成器
     * @param task
     */
    private void makeGenerators(Task task) {
        if (this.cookieGenerator != null) {
            if (!(this.cookieGenerator instanceof CookieGenerator)) {
                this.setCookie(this.cookieGenerator.get(task));
            }
        }
        if (this.headerGenerator != null) {
            if (!(this.headerGenerator instanceof NoHeaderGenerator)) {
                Map headers = this.headerGenerator.get(task);
                this.setHttpHeader(headers);
            }
        }
    }

    @Override
    public HttpClient proxy() {
        if(this.proxy != null && !this.proxy.isEmpty()){
            try {
                this.client.proxy();
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
        return this;
    }

    @Override
    public TaskResponse doPost(Task task) {
        try {
            return this.client.doPost(task);
        } catch (Exception e) {
            //TODO
            e.printStackTrace();
            return TaskResponse.empty().setTask(task);
        }
    }

    @Override
    public HttpClient setCookie(String cookie) {
        try {
            this.client.setCookie(cookie);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return this;
    }

    @Override
    public HttpClient setHttpHeader(Map<String, String> httpHeader) {
        try {
            this.client.setHttpHeader(httpHeader);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return this;
    }

    @Override
    public ProxyTuple getCurrentProxyTuple() {
        try {
            return this.client.getCurrentProxyTuple();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public HttpClient showProgress(Boolean show) {
        this.client.showProgress(show);
        return this;
    }

    public HttpClientProxy setCookieGenerator(StringGenerator cookieGenerator){
        this.cookieGenerator = cookieGenerator;
        return this;
    }

    public HttpClientProxy setHeaderGenerator(MapGenerator headerGenerator) {
        this.headerGenerator = headerGenerator;
        return this;
    }
}
