package com.pgb.spider.http.exception;

import com.pgb.spider.utils.SpiderUtils;

/**
 * @author Clayburn
 * 重定向
 */
public class Http302Exception extends Http30XException  {
    private static final int CODE = 302;
    public Http302Exception() {
    }

    public Http302Exception(String message) {
        super(SpiderUtils.exceptionMessage(CODE,message));
    }

    public Http302Exception(String message, Throwable cause) {
        super(SpiderUtils.exceptionMessage(CODE,message), cause);
    }

    public Http302Exception(Throwable cause) {
        super(cause);
    }

    public Http302Exception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(SpiderUtils.exceptionMessage(CODE,message), cause, enableSuppression, writableStackTrace);
    }
}
