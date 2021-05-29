package com.markben.beans.exception;

/**
 * 框架运行时异常
 * @autor 乌草坡
 * @since 0.0.1
 */
public class MarkbenStatusException extends RuntimeException {

    private int status;

    private String msg;

    public MarkbenStatusException(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public MarkbenStatusException(String message, int status, String msg) {
        super(message);
        this.status = status;
        this.msg = msg;
    }

    public MarkbenStatusException(String message, Throwable cause, int status, String msg) {
        super(message, cause);
        this.status = status;
        this.msg = msg;
    }

    public MarkbenStatusException(Throwable cause, int status, String msg) {
        super(cause);
        this.status = status;
        this.msg = msg;
    }

    public MarkbenStatusException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int status, String msg) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.status = status;
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
