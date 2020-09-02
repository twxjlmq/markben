package com.markben.core.exception;

/**
 * @autor 乌草坡 2020-09-02
 * @since 1.0
 */
public class SystemInitializationException extends RuntimeException {

    public SystemInitializationException() {
    }

    public SystemInitializationException(String message) {
        super(message);
    }

    public SystemInitializationException(String message, Throwable cause) {
        super(message, cause);
    }

    public SystemInitializationException(Throwable cause) {
        super(cause);
    }

    public SystemInitializationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
