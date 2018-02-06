package com.pgb.spider.http.exception;


import com.pgb.spider.utils.SpiderUtils;

/**
 * @author Clayburn
 * HTTP Version Not Supported/不支持的 HTTP 版本
 */
public class Http505Exception extends Http50XException  {
    private static final int CODE = 505;
    public Http505Exception() {
    }

    public Http505Exception(String message) {
        super(SpiderUtils.exceptionMessage(CODE,message));
    }

    public Http505Exception(String message, Throwable cause) {
        super(SpiderUtils.exceptionMessage(CODE,message), cause);
    }

    public Http505Exception(Throwable cause) {
        super(cause);
    }

    public Http505Exception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(SpiderUtils.exceptionMessage(CODE,message), cause, enableSuppression, writableStackTrace);
    }
}
