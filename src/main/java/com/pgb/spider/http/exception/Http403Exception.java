package com.pgb.spider.http.exception;


import com.pgb.spider.utils.SpiderUtils;

/**
 * @author Clayburn
 * Forbidden/禁止
 */
public class Http403Exception extends Http40XException  {
    private static final int CODE = 403;
    public Http403Exception() {
    }

    public Http403Exception(String message) {
        super(SpiderUtils.exceptionMessage(CODE,message));
    }

    public Http403Exception(String message, Throwable cause) {
        super(SpiderUtils.exceptionMessage(CODE,message), cause);
    }

    public Http403Exception(Throwable cause) {
        super(cause);
    }

    public Http403Exception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(SpiderUtils.exceptionMessage(CODE,message), cause, enableSuppression, writableStackTrace);
    }
}
