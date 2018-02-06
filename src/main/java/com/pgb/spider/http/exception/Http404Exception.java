package com.pgb.spider.http.exception;

import com.pgb.spider.utils.SpiderUtils;

/**
 * @author Clayburn
 * Not Found/未找到
 */
public class Http404Exception extends Http40XException {
    private static final int CODE = 404;
    public Http404Exception() {
    }

    public Http404Exception(String message) {
        super(SpiderUtils.exceptionMessage(CODE,message));
    }

    public Http404Exception(String message, Throwable cause) {
        super(SpiderUtils.exceptionMessage(CODE,message), cause);
    }

    public Http404Exception(Throwable cause) {
        super(cause);
    }

    public Http404Exception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(SpiderUtils.exceptionMessage(CODE,message), cause, enableSuppression, writableStackTrace);
    }
}
