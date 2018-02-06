package com.pgb.spider.http.exception;

import com.pgb.spider.utils.SpiderUtils;

/**
 * @author Clayburn
 * Request Timeout/请求超时
 */
public class Http408Exception extends Http40XException  {
    private static final int CODE = 408;
    public Http408Exception() {
    }

    public Http408Exception(String message) {
        super(SpiderUtils.exceptionMessage(CODE,message));
    }

    public Http408Exception(String message, Throwable cause) {
        super(SpiderUtils.exceptionMessage(CODE,message), cause);
    }

    public Http408Exception(Throwable cause) {
        super(cause);
    }

    public Http408Exception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(SpiderUtils.exceptionMessage(CODE,message), cause, enableSuppression, writableStackTrace);
    }
}
