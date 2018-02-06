package com.pgb.spider.http.exception;


import com.pgb.spider.utils.SpiderUtils;

/**
 * @author Clayburn
 * @date : 2018/1/16 18:10
 * @description
 */
public class Http300Exception extends Http30XException  {
    private static final int CODE = 300;
    public Http300Exception() {
    }

    public Http300Exception(String message) {
        super(SpiderUtils.exceptionMessage(CODE,message));
    }

    public Http300Exception(String message, Throwable cause) {
        super(SpiderUtils.exceptionMessage(CODE,message), cause);
    }

    public Http300Exception(Throwable cause) {
        super(cause);
    }

    public Http300Exception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(SpiderUtils.exceptionMessage(CODE,message), cause, enableSuppression, writableStackTrace);
    }
}
