package com.markben.core.cache.ehcache;

import com.markben.common.exception.NullArgumentException;
import com.markben.common.utils.StringUtils;
import com.markben.core.cache.CacheException;
import com.markben.core.cache.ICache;
import com.markben.core.cache.ICacheManager;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.expiry.Duration;
import org.ehcache.expiry.Expirations;

import java.util.concurrent.TimeUnit;

/**
 * ehcache缓存管理
 * @author lmq  2017年6月28日
 * @version 1.0
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
                cacheConfiguration = cacheCfgBuilder.withExpiry(Expirations.timeToLiveExpiration(Duration.of(expiry, TimeUnit.MILLISECONDS))).build();
                cache = cacheManager.createCache(name, cacheConfiguration);
            } else {
                cacheConfiguration = cacheCfgBuilder.withExpiry(Expirations.noExpiration()).build();
                cache = cacheManager.createCache(name, cacheConfiguration);
            }
        }
        Ehcache ehCache = new Ehcache<Object, Object>(cache);
        return ehCache;
    }

    @Override
    public void destroy() throws CacheException {
        cacheManager.close();
    }

}
