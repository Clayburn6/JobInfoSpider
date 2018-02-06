package com.pgb.spider.http.exception;

import com.pgb.spider.utils.SpiderUtils;

/**
 * @author Clayburn
 * Internal Server Error/内部服务器错误
 */
public class Http500Exception extends Http50XException  {
    private static final int CODE = 500;
    public Http500Exception() {
    }

    public Http500Exception(String message) {
        super(SpiderUtils.exceptionMessage(CODE,message));
    }

    public Http500Exception(String message, Throwable cause) {
        super(SpiderUtils.exceptionMessage(CODE,message), cause);
    }

    public Http500Exception(Throwable cause) {
        super(cause);
    }

    public Http500Exception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(SpiderUtils.exceptionMessage(CODE,message), cause, enableSuppression, writableStackTrace);
    }
}
