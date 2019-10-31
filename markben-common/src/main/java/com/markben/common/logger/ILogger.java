package com.markben.common.logger;

/**
 * @author 乌草坡
 * @since 1.0
 */
public interface ILogger {

    boolean isTraceEnabled();

    void trace(String msg);

    void trace(String format, Object... args);

    boolean isDebugEnabled();

    void debug(String msg);

    void debug(String format, Object... args);

    boolean isInfoEnabled();

    void info(String msg);

    void info(String format, Object... args);

    boolean isWarnEnabled();

    void warn(String msg);

    void warn(String format, Object... args);

    void warn(String msg, Throwable t);

    boolean isErrorEnabled();

    void error(String msg);

    void error(String format, Object... args);

    void error(String msg, Throwable t);

}
