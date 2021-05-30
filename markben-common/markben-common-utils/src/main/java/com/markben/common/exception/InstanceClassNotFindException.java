package com.markben.common.exception;

/**
 * 实例类未找到异常
 * @author 乌草坡
 * @since 0.0.1
 */
public class InstanceClassNotFindException extends RuntimeException {

    public InstanceClassNotFindException() {
        this("未找到实例类");
    }

    public InstanceClassNotFindException(String message) {
        super(message);
    }

    public InstanceClassNotFindException(String message, Throwable cause) {
        super(message, cause);
    }

    public InstanceClassNotFindException(Throwable cause) {
        super(cause);
    }

    public InstanceClassNotFindException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
