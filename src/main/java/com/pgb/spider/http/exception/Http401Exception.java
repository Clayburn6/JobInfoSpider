package com.pgb.spider.http.exception;

import com.pgb.spider.utils.SpiderUtils;

/**
 * @author Clayburn
 * Bad Request/错误请求
 */
public class Http401Exception extends Http40XException  {
    private static final int CODE = 401;
    public Http401Exception() {
    }

    public Http401Exception(String message) {
        super(SpiderUtils.exceptionMessage(CODE,message));
    }

    public Http401Exception(String message, Throwable cause) {
        super(SpiderUtils.exceptionMessage(CODE,message), cause);
    }

    public Http401Exception(Throwable cause) {
        super(cause);
    }

    public Http401Exception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(SpiderUtils.exceptionMessage(CODE,message), cause, enableSuppression, writableStackTrace);
    }
}
