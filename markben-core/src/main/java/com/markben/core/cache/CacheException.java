package com.markben.core.cache;

/**
 * 缓存异常
 * @author 乌草坡
 * @version 1.0
 */
public class CacheException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1819537238013814177L;

	/**
	 * 创建缓存异常
	 */
	public CacheException() {
		super();
	}

	/**
	 * 创建缓存异常
	 * @param message
	 * @param cause
	 */
	public CacheException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * 创建缓存异常
	 * @param message
	 */
	public CacheException(String message) {
		super(message);
	}

	/**
	 * 创建缓存异常
	 * @param cause
	 */
	public CacheException(Throwable cause) {
		super(cause);
	}
}
