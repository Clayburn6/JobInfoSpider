package com.pgb.spider.http.exception;

import com.pgb.spider.utils.SpiderUtils;

/**
 * @author Clayburn
 * ProxyConfig Authentication Required/代理服务器认证要求
 */
public class Http407Exception extends Http40XException  {
    private static final int CODE = 407;
    public Http407Exception() {
    }

    public Http407Exception(String message) {
        super(SpiderUtils.exceptionMessage(CODE,message));
    }

    public Http407Exception(String message, Throwable cause) {
        super(SpiderUtils.exceptionMessage(CODE,message), cause);
    }

    public Http407Exception(Throwable cause) {
        super(cause);
    }

    public Http407Exception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(SpiderUtils.exceptionMessage(CODE,message), cause, enableSuppression, writableStackTrace);
    }
}
