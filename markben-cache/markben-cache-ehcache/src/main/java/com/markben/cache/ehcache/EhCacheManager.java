package com.markben.cache.ehcache;

import com.markben.cache.CacheException;
import com.markben.cache.ICache;
import com.markben.cache.ICacheManager;
import com.markben.common.exception.NullArgumentException;
import com.markben.common.utils.StringUtils;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;

import java.time.Duration;

/**
 * ehcache缓存管理
 * @author 乌草坡
 * @since 1.0
 */
public class EhCacheManager implements ICacheManager {

    private CacheManager cacheManager = null;
    
    public EhCacheManager() {
        cacheManager = CacheManagerBuilder.newCacheManagerBuilder() 
                .withCache("preConfigured",
                    CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class, ResourcePoolsBuilder.heap(1)))
                .build(); 
                cacheManager.init();
    }

    @Override
    public <K, V> ICache<K, V> getCache(String name) throws CacheException {
        return getCache(name, null);
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public <K, V> ICache<K, V> getCache(String name, Long expiry) throws CacheException {
        if(StringUtils.isEmpty(name)) {
            throw new NullArgumentException();
        }
        Cache<Object, Object> cache = cacheManager.getCache(name, Object.class, Object.class);
        if(null == cache){
            CacheConfigurationBuilder<Object, Object> cacheCfgBuilder = CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class, ResourcePoolsBuilder.heap(100));
            CacheConfiguration<Object, Object> cacheConfiguration = null;
            if(null != expiry) {
                cacheConfiguration = cacheCfgBuilder.withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofMillis(expiry))).build();
                cache = cacheManager.createCache(name, cacheConfiguration);
            } else {
                cacheConfiguration = cacheCfgBuilder.withExpiry(ExpiryPolicyBuilder.noExpiration()).build();
                cache = cacheManager.createCache(name, cacheConfiguration);
            }
        }
        Ehcache ehCache = new Ehcache<Object, Object>(cache);
        return ehCache;
    }

    @Override
    public <K, V> ICache<K, V> getCacheIfAbsent(String name) throws CacheException {
        if(StringUtils.isEmpty(name)) {
            throw new NullArgumentException();
        }
        Cache<Object, Object> cache = cacheManager.getCache(name, Object.class, Object.class);
        if(null == cache){
            return null;
        }
        Ehcache ehCache = new Ehcache<Object, Object>(cache);
        return ehCache;
    }

    @Override
    public void destroy() throws CacheException {
        cacheManager.close();
    }

}
