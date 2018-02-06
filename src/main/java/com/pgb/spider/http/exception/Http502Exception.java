package com.pgb.spider.http.exception;

import com.pgb.spider.utils.SpiderUtils;

/**
 * @author Clayburn
 * Bad Gateway/错误的网关
 */
public class Http502Exception extends Http50XException  {
    private static final int CODE = 502;
    public Http502Exception() {
    }

    public Http502Exception(String message) {
        super(SpiderUtils.exceptionMessage(CODE,message));
    }

    public Http502Exception(String message, Throwable cause) {
        super(SpiderUtils.exceptionMessage(CODE,message), cause);
    }

    public Http502Exception(Throwable cause) {
        super(cause);
    }

    public Http502Exception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(SpiderUtils.exceptionMessage(CODE,message), cause, enableSuppression, writableStackTrace);
    }
}
