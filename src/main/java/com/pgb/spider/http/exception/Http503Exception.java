package com.pgb.spider.http.exception;


import com.pgb.spider.utils.SpiderUtils;

/**
 * @author Clayburn
 * Service Unavailable/服务无法获得
 */
public class Http503Exception extends Http50XException  {
    private static final int CODE = 503;
    public Http503Exception() {
    }

    public Http503Exception(String message) {
        super(SpiderUtils.exceptionMessage(CODE,message));
    }

    public Http503Exception(String message, Throwable cause) {
        super(SpiderUtils.exceptionMessage(CODE,message), cause);
    }

    public Http503Exception(Throwable cause) {
        super(cause);
    }

    public Http503Exception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(SpiderUtils.exceptionMessage(CODE,message), cause, enableSuppression, writableStackTrace);
    }
}
