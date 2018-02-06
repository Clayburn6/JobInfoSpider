package com.pgb.spider.http.exception;

import com.pgb.spider.utils.SpiderUtils;

/**
 * @author Clayburn
 * Not Acceptable/无法访问
 */
public class Http406Exception extends Http40XException  {
    private static final int CODE = 406;
    public Http406Exception() {
    }

    public Http406Exception(String message) {
        super(SpiderUtils.exceptionMessage(CODE,message));
    }

    public Http406Exception(String message, Throwable cause) {
        super(SpiderUtils.exceptionMessage(CODE,message), cause);
    }

    public Http406Exception(Throwable cause) {
        super(cause);
    }

    public Http406Exception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(SpiderUtils.exceptionMessage(CODE,message), cause, enableSuppression, writableStackTrace);
    }
}
