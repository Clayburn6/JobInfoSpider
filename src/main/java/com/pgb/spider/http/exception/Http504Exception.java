package com.pgb.spider.http.exception;


import com.pgb.spider.utils.SpiderUtils;

/**
 * @author Clayburn
 * Gateway Timeout/网关超时
 */
public class Http504Exception extends Http50XException  {
    private static final int CODE = 504;
    public Http504Exception() {
    }

    public Http504Exception(String message) {
        super(SpiderUtils.exceptionMessage(CODE,message));
    }

    public Http504Exception(String message, Throwable cause) {
        super(SpiderUtils.exceptionMessage(CODE,message), cause);
    }

    public Http504Exception(Throwable cause) {
        super(cause);
    }

    public Http504Exception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(SpiderUtils.exceptionMessage(CODE,message), cause, enableSuppression, writableStackTrace);
    }
}
