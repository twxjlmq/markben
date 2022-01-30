package com.markben.cache.utils;


import com.markben.cache.ICache;
import com.markben.cache.ICacheManager;
import com.markben.common.utils.StringUtils;

/**
 * 缓存管理工具类
 * @author 乌草坡 2021-12-07
 * @since 0.0.1
 */
public class CacheManagerUtils {

    private static ICacheManager cacheManager;

    public static void setCacheManager(ICacheManager cacheManager) {
        CacheManagerUtils.cacheManager = cacheManager;
    }

    /**
     * 放入缓存
     * @param key 缓存名称
     * @param value 缓存值
     * @param <T> 值类型
     */
    public static <T> void putCache(String key, T value) {
        putCache(key, value, 0);
    }

    /**
     * 放入缓存，设置超时时间；
     * @param key 缓存名称
     * @param value 缓存值
     * @param expiry 过期时间，单位：毫秒；如果小于等于0，则没有过期时间
     * @param <T> 值类型
     */
    public static <T> void putCache(String key, T value, long expiry) {
        StringUtils.isAssert(key, "缓存KEY不能为空");
        StringUtils.isAssert(value, "缓存内容不能为空");
        ICache<String, T> cache = null;
        if(expiry > 0) {
            cache = cacheManager.getCache(key, expiry);
        } else {
            cache = cacheManager.getCache(key);
        }
        cache.put(key, value);
    }

    /**
     * 获取缓存值
     * @param key 缓存名称
     * @param <T> 返回类型
     * @return 返回缓存值，如果存在；否则返回null
     */
    public static <T> T getCache(String key) {
        StringUtils.isAssert(key, "缓存KEY不能为空");
        ICache<String, T> cache = cacheManager.getCacheIfNotAbsent(key);
        if(null != cache) {
            return cache.get(key);
        }
        return null;
    }

    /**
     * 移除缓存
     * @param key 缓存名称
     */
    public static void removeCache(String key) {
        StringUtils.isAssert(key, "缓存KEY不能为空");
        cacheManager.remove(key);
    }
}
