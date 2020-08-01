package com.markben.cache;

/**
 * 该接口的实现类，设置cache管理器 
 * <p> 实现该接口的类，系统会自动给实现类注入缓存管理实例类;</p>
 * 该类（实现类）就可以操作缓存数据信息了
 * @author 乌草坡
 * @since 1.0
 *
 */
public interface ICacheManagerAware {

	/**
	 * 设置cache管理器
	 * @param cacheManager
	 */
	public void setCacheManager(ICacheManager cacheManager);
	
}
