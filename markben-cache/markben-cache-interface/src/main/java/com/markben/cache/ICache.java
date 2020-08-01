package com.markben.cache;


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
	 * @param key KEY
	 * @return V 返回缓存值
	 */
	V get(K key);
	
	/**
	 * 添加到缓存
	 * @param key KEY
	 * @param value 缓存值
	 * @return V
	 */
	V put(K key, V value);

	/**
	 * 添加到缓存
	 * @param key KEY
	 * @param value 缓存值
	 * @param expire 到期时间；单位秒
	 * @return
	 */
	//V put(K key, V value, long expire);
	
	/**
	 * 从缓存中删除指定key的对象
	 * @param key KEY
	 * @return V
	 */
	V remove(K key);
	
	/**
	 * 清空缓存
	 */
	void clear();
	
}
