package com.pgb.spider.http.handler;

import com.pgb.spider.executer.response.TaskErrorResponse;

/**
 * @author Clayburn
 * @date : 2018/1/16 18:44
 * @description 任务失败回调
 */
public interface ITaskErrorHandler {
    void error(TaskErrorResponse response);
}
