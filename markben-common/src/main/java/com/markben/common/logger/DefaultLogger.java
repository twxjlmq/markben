package com.markben.common.logger;

import org.slf4j.Logger;

/**
 * 默认日志实现类
 * @author 乌草坡
 * @since 1.0
 */
public class DefaultLogger implements ILogger {

    private Logger logger;

    public DefaultLogger(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void trace(String msg) {
        logger.trace(msg);
    }

    @Override
    public void trace(String format, Object... args) {
        logger.trace(format, args);
    }

    @Override
    public void debug(String msg) {
        logger.debug(msg);
    }

    @Override
    public void debug(String format, Object... args) {
        logger.debug(format, args);
    }

    @Override
    public void info(String msg) {
        logger.info(msg);
    }

    @Override
    public void info(String format, Object... args) {
        logger.info(format, args);
    }

    @Override
    public void warn(String msg) {
        logger.warn(msg);
    }

    @Override
    public void warn(String format, Object... args) {
        logger.warn(format, args);
    }

    @Override
    public void warn(String msg, Throwable t) {
        logger.warn(msg, t);
    }

    @Override
    public void error(String msg) {
        logger.error(msg);
    }

    @Override
    public void error(String format, Object... args) {
        logger.error(format, args);
    }

    @Override
    public void error(String msg, Throwable t) {
        logger.error(msg, t);
    }

    @Override
    public boolean isTraceEnabled() {
        return logger.isTraceEnabled();
    }

    @Override
    public boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }

    @Override
    public boolean isInfoEnabled() {
        return logger.isInfoEnabled();
    }

    @Override
    public boolean isWarnEnabled() {
        return logger.isWarnEnabled();
    }

    @Override
    public boolean isErrorEnabled() {
        return logger.isErrorEnabled();
    }
}
