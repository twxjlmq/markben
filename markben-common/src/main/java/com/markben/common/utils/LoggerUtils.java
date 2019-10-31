package com.markben.common.utils;

import com.markben.common.exception.NullArgumentException;
import com.markben.common.logger.DefaultLogger;
import com.markben.common.logger.ILogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日志工具类
 * @author 乌草坡
 * @since 1.0
 */
public class LoggerUtils {

	/**
	 * 获取日志对象
	 * @param clazz 对象类型
	 * @return 返回日志对象
	 */
	public static ILogger getLogger(Class<?> clazz) {
		if(null == clazz) {
			throw new NullArgumentException("传入的参数[clazz]为空");
		}
		return new DefaultLogger(LoggerFactory.getLogger(clazz));
	}

	/**
	 * 记录INFO日志；如果 <code>msg</code> 参数需要拼接字符串，请使用{@link #info(Logger, String, Object...)} 方法
	 * @param logger 日志实现类
	 * @param msg 日志内容
	 */
	public static void info(ILogger logger, String msg) {
		if(logger.isInfoEnabled() && StringUtils.isNotEmpty(msg)) {
			logger.info(msg);
		}
	}
	
	/**
	 * 记录INFO日志；拼接例子，如：info("你好啊,{}","张三")
	 * @param logger 日志实现类
	 * @param msg 日志内容
	 * @param args 日志参数
	 */
	public static void info(ILogger logger, String msg, Object...args ) {
		if(logger.isInfoEnabled() && StringUtils.isNotEmpty(msg)) {
			logger.info(msg, args);
		}
	}
	
	/**
	 * 记录DEBUG日志；如果 <code>msg</code> 参数需要拼接字符串，请使用{@link #debug(Logger, String, Object...)} 方法
	 * @param logger 日志实现类
	 * @param msg 日志内容
	 */
	public static void debug(ILogger logger, String msg) {
		if(logger.isDebugEnabled() && StringUtils.isNotEmpty(msg)) {
			logger.debug(msg);
		}
	}
	
	/**
	 * 记录DEBUG日志；拼接例子，如：info("你好啊,{}",new String[]{"张三"})
	 * @param logger 日志实现类
	 * @param msg 日志内容
	 * @param args 日志参数
	 */
	public static void debug(ILogger logger, String msg, Object...args ) {
		if(logger.isDebugEnabled() && StringUtils.isNotEmpty(msg)) {
			logger.debug(msg, args);
		}
	}
	
	/**
	 * 记录ERROR日志
	 * @param logger 日志实现类
	 * @param msg 日志内容
	 */
	public static void error(ILogger logger, String msg) {
		if(logger.isErrorEnabled() && StringUtils.isNotEmpty(msg)) {
			logger.error(msg);
		}
	}
	
	/**
	 * 记录ERROR日志
	 * @param logger 日志实现类
	 * @param msg 日志内容
	 * @param args 日志参数
	 */
	public static void error(ILogger logger, String msg, Object...args ) {
		if(logger.isErrorEnabled() && StringUtils.isNotEmpty(msg)) {
			logger.error(msg, args);
		}
	}
	
	/**
     * 记录ERROR日志
     * @param logger 日志实现类
     * @param msg 日志内容
     * @param t 异常对象
     */
    public static void error(ILogger logger, String msg, Throwable t) {
        if(logger.isErrorEnabled() && StringUtils.isNotEmpty(msg)) {
            logger.error(msg, t);
        }
    }
    
    /**
     * 记录ERROR日志
     * @param logger 日志实现类
     * @param t 异常对象
     */
    public static void error(ILogger logger, Throwable t) {
        if(logger.isErrorEnabled()) {
            logger.error("异常信息", t);
        }
    }
	
	/**
	 * 记录WARN日志
	 * @param logger 日志实现类
	 * @param msg 日志内容
	 */
	public static void warn(ILogger logger, String msg) {
		if(logger.isWarnEnabled() && StringUtils.isNotEmpty(msg)) {
			logger.warn(msg);
		}
	}
	
	/**
	 * 记录WARN日志
	 * @param logger 日志实现类
	 * @param msg 日志内容
	 * @param args 日志参数
	 */
	public static void warn(ILogger logger, String msg, Object...args ) {
		if(logger.isWarnEnabled() && StringUtils.isNotEmpty(msg)) {
			logger.warn(msg, args);
		}
	}
	
	/**
	 * 记录TRACE日志
	 * @param logger 日志实现类
	 * @param msg 日志内容
	 */
	public static void trace(ILogger logger, String msg) {
		if(logger.isTraceEnabled() && StringUtils.isNotEmpty(msg)) {
			logger.trace(msg);
		}
	}
	
	/**
	 * 记录TRACE日志
	 * @param logger 日志实现类
	 * @param msg 日志内容
	 * @param args 日志参数
	 */
	public static void trace(ILogger logger, String msg, Object...args ) {
		if(logger.isTraceEnabled() && StringUtils.isNotEmpty(msg)) {
			logger.trace(msg, args);
		}
	}
}
