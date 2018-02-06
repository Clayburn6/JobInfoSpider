package com.pgb.spider.http.exception;

import com.pgb.spider.utils.SpiderUtils;

/**
 * @author Clayburn
 * Not Implemented/未实现
 */
public class Http501Exception extends Http50XException  {
    private static final int CODE = 501;
    public Http501Exception() {
    }

    public Http501Exception(String message) {
        super(SpiderUtils.exceptionMessage(CODE,message));
    }

    public Http501Exception(String message, Throwable cause) {
        super(SpiderUtils.exceptionMessage(CODE,message), cause);
    }

    public Http501Exception(Throwable cause) {
        super(cause);
    }

    public Http501Exception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(SpiderUtils.exceptionMessage(CODE,message), cause, enableSuppression, writableStackTrace);
    }
}
