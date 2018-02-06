package com.pgb.spider.http.exception;

import com.pgb.spider.utils.SpiderUtils;

/**
 * @author Clayburn
 * Unauthorized/未授权
 */
public class Http400Exception extends Http40XException  {
    private static final int CODE = 400;
    public Http400Exception() {
    }

    public Http400Exception(String message) {
        super(SpiderUtils.exceptionMessage(CODE,message));
    }

    public Http400Exception(String message, Throwable cause) {
        super(SpiderUtils.exceptionMessage(CODE,message), cause);
    }

    public Http400Exception(Throwable cause) {
        super(cause);
    }

    public Http400Exception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(SpiderUtils.exceptionMessage(CODE,message), cause, enableSuppression, writableStackTrace);
    }
}
