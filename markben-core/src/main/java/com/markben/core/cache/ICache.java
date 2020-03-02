package com.markben.core.cache;


/**
 * 缓存接口
 * @author 乌草坡
 * @version 1.0
 * @param <K>
 * @param <V>
 */
public interface ICache<K,V> {

	/**
	 * 根据key中缓存中获取对应的值
	 * @param key
	 * @return V
	 */
	V get(K key);
	
	/**
	 * 添加到缓存
	 * @param key
	 * @param value
	 * @return V
	 */
	V put(K key, V value);
	
	/**
	 * 从缓存中删除指定key的对象
	 * @param key
	 * @return V
	 */
	V remove(K key);
	
	/**
	 * 清空缓存
	 */
	void clear();
	
}
