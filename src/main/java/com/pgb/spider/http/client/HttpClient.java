package com.pgb.spider.http.client;

import com.pgb.spider.executer.Task;
import com.pgb.spider.executer.response.TaskResponse;
import com.pgb.spider.http.HttpProxy;
import com.pgb.spider.http.ProxyTuple;

import java.util.Map;

/**
 * @author Clayburn
 * @date : 2018/1/16 18:10
 * @description
 */
public interface HttpClient {
    /**
     * 为该HttpClient设置代理
     * @param proxy
     * @return
     * @throws Exception
     */
    HttpClient setProxy(HttpProxy proxy) throws Exception;

    /**
     * 进行GET请求
     * @param task
     * @return
     * @throws Exception
     */
    TaskResponse doGet(Task task) throws Exception;

    HttpClient proxy() throws Exception;

    TaskResponse doPost(Task task) throws Exception;

    HttpClient setCookie(String cookie) throws Exception;

    HttpClient setHttpHeader(Map<String, String> httpHeader) throws Exception;

    ProxyTuple getCurrentProxyTuple() throws Exception;

    HttpClient showProgress(Boolean show);
}
