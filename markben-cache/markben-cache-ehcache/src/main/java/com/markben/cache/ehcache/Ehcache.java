package com.markben.cache.ehcache;

import com.markben.cache.ICache;
import org.ehcache.Cache;

/**
 * Ehcache缓存实现类
 * @param <K>
 * @param <V>
 */
public class Ehcache<K, V> implements ICache<K, V> {

    private Cache<K, V> cache;
    
    public Ehcache( Cache<K, V> cache) {
        this.cache = cache; 
    }

    @Override
    public V get(K key) {
        return cache.get(key);
    }

    @Override
    public V put(K key, V value) {
        cache.put(key, value);
        return value;
    }

    @Override
    public V remove(K key) {
        V value = cache.get(key);
        cache.remove(key);
        return value;
    }

    @Override
    public void clear() {
        cache.clear();
    }
    
}
