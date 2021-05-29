package com.markben.core.context;

/**
 * 获取Markben上下文接口
 * @author 乌草坡
 * @since 0.0.1
 */
public interface MarkbenContextAware {

	/**
	 * 设置上下文
	 * @param context
	 */
	void setContext(MarkbenContext context);

}
