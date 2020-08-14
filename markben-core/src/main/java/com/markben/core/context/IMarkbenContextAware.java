package com.markben.core.context;

/**
 * 服务查询接口
 * @author 乌草坡
 * @since 1.0
 */
public interface IMarkbenContextAware {

	/**
	 * 设置上下文
	 * @param context
	 */
	void setContext(IMarkbenContext context);

}
