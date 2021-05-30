package com.markben.common.exception;

/**
 * 文件不存在异常
 * @author 乌草坡
 * @since 0.0.1
 */
public class FileNotExistException extends RuntimeException {

    public FileNotExistException() {
        super("文件不存在");
    }

    public FileNotExistException(String message) {
        super(message);
    }

    /**
     *
     * @param message
     * @param isFilePath <code>message</code>参数值是否为文件路径；
     *                   如果<code>isFilePath</code>值为true；表示<code>message</code>是文件路径；否则不是文件路径
     */
    public FileNotExistException(String message, boolean isFilePath) {
        super((isFilePath) ? "该文件["+message+"]不存在" : message);
    }

    public FileNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileNotExistException(Throwable cause) {
        super(cause);
    }

    protected FileNotExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
