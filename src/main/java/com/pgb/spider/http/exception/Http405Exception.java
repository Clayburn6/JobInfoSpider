package com.pgb.spider.http.exception;

import com.pgb.spider.utils.SpiderUtils;

/**
 * @author Clayburn
 * Method Not Allowed/方法未允许
 */
public class Http405Exception extends Http40XException  {
    private static final int CODE = 405;
    public Http405Exception() {
    }

    public Http405Exception(String message) {
        super(SpiderUtils.exceptionMessage(CODE,message));
    }

    public Http405Exception(String message, Throwable cause) {
        super(SpiderUtils.exceptionMessage(CODE,message), cause);
    }

    public Http405Exception(Throwable cause) {
        super(cause);
    }

    public Http405Exception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(SpiderUtils.exceptionMessage(CODE,message), cause, enableSuppression, writableStackTrace);
    }
}
