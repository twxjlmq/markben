package com.markben.cache;


/**
 * 缓存管理器接口，该接口提供具体的cache实现
 * @author 乌草坡
 * @version 1.0
 */
public interface ICacheManager {

	/**
	 * 根据名称获取缓存，如果该名称对应的缓存，不存在，则创建一个新的缓存并返回
	 * @param name
	 * @param expiry 超期时间，单位：毫秒
	 * @return ICache<K, V>
	 * @throws CacheException
	 */
	<K, V> ICache<K, V> getCache(String name, Long expiry) throws CacheException;
	
	/**
     * 根据名称获取缓存，如果该名称对应的缓存，不存在，则创建一个新的缓存并返回
     * @param name
     * @return ICache<K, V>
     * @throws CacheException
     */
    <K, V> ICache<K, V> getCache(String name) throws CacheException;

	/**
	 * 根据名称获取缓存，如果该名称对应的缓存不存在，否则返回null
	 * @param name 名称
	 * @return ICache<K, V>
	 * @throws CacheException
	 */
	<K, V> ICache<K, V>  getCacheIfAbsent(String name) throws CacheException;
	
	/**
	 * 销毁缓存
	 * @throws CacheException
	 */
	void destroy() throws CacheException;
	
}
