package com.pgb.spider.http.client;

import com.pgb.spider.executer.Task;
import com.pgb.spider.executer.response.TaskResponse;
import com.pgb.spider.http.HttpProxy;
import com.pgb.spider.http.ProxyTuple;
import com.pgb.spider.http.handler.ITaskErrorHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Clayburn
 * @date : 2018/1/16 18:55
 * @description
 */
public abstract class AbstractHttpClient implements HttpClient{
    protected HttpProxy proxy;
    protected ProxyTuple proxyTuple;
    protected String cookie;
    protected Map<String, String> httpHeader = new HashMap<String,String>();
    protected ITaskErrorHandler taskErrorHandler;

    @Override
    public HttpClient setProxy(HttpProxy proxy) throws Exception {
        this.proxy = proxy;
        return this;
    }

    @Override
    public abstract TaskResponse doGet(Task task) throws Exception;

    @Override
    public abstract HttpClient proxy() throws Exception;

    @Override
    public abstract TaskResponse doPost(Task task) throws Exception;

    @Override
    public abstract HttpClient setCookie(String cookie) throws Exception;

    @Override
    public HttpClient setHttpHeader(Map<String, String> httpHeader) throws Exception {
        this.httpHeader = httpHeader;
        return this;
    }

    @Override
    public ProxyTuple getCurrentProxyTuple() throws Exception {
        return proxyTuple;
    }
}
