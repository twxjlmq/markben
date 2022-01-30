package com.markben.restful.common.exception;

/**
 * 未登录异常
 * @author 乌草坡
 * @since 0.0.1
 */
public class NotLoginException extends RuntimeException {

    public NotLoginException() {
        super("用户未登录");
    }

    public NotLoginException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public NotLoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotLoginException(String message) {
        super(message);
    }

    public NotLoginException(Throwable cause) {
        super(cause);
    }

    
    
}
