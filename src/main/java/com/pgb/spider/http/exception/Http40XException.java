package com.pgb.spider.http.exception;

/**
 * @author Clayburn
 * Multiple Choices/多重选择
 */
public class Http40XException extends HttpException  {
    public Http40XException() {
    }

    public Http40XException(String message) {
        super(message);
    }

    public Http40XException(String message, Throwable cause) {
        super(message, cause);
    }

    public Http40XException(Throwable cause) {
        super(cause);
    }

    public Http40XException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
