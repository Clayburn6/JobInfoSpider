package com.pgb.spider.http.exception;

import com.pgb.spider.utils.SpiderUtils;

/**
 * @author Clayburn
 * Moved Permanently/被请求的资源已永久移动到新位置，并且将来任何对此资源的引用都应该使用本响应返回的若干个URI之一
 */
public class Http301Exception extends Http30XException  {
    private static final int CODE = 301;
    public Http301Exception() {
    }

    public Http301Exception(String message) {
        super(SpiderUtils.exceptionMessage(CODE,message));
    }

    public Http301Exception(String message, Throwable cause) {
        super(SpiderUtils.exceptionMessage(CODE,message), cause);
    }

    public Http301Exception(Throwable cause) {
        super(cause);
    }

    public Http301Exception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(SpiderUtils.exceptionMessage(CODE,message), cause, enableSuppression, writableStackTrace);
    }
}
