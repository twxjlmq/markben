package com.markben.rest.common.exception;

/**
 * 接口参数异常
 * @author 乌草坡
 * @since 1.0
 */
public class RestArgumentException extends RuntimeException {

    public RestArgumentException() {
        super();
    }

    public RestArgumentException(String message, Throwable cause, boolean enableSuppression,
                                 boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public RestArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    public RestArgumentException(String message) {
        super(message);
    }

    public RestArgumentException(Throwable cause) {
        super(cause);
    }

}
