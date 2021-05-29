package com.markben.common.exception;

/**
 * 定义参数为空的异常
 * @author 乌草坡
 * @since 0.0.1
 */
public class NullArgumentException extends IllegalArgumentException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4969761645495788894L;

	public NullArgumentException() {
		this("提供的参数为空");
	}
	
	public NullArgumentException(String message) {
		super(message);
	}
	
	public NullArgumentException(Throwable cause) {
		super(cause);
	}
	
	public NullArgumentException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
