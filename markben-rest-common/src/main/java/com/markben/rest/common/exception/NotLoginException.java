package com.markben.rest.common.exception;

/**
 * 未登录异常
 * @author 乌草坡
 * @since 1.0
 */
public class NotLoginException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = -1600503002305863077L;

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
