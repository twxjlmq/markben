package com.markben.cache.redis;

import com.markben.cache.CacheException;
import com.markben.cache.ICacheManager;
import com.markben.common.config.SystemConfig;
import com.markben.common.logger.Logger;
import com.markben.common.utils.LoggerUtils;
import com.markben.common.utils.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * redis缓存管理对象
 * @author 乌草坡
 * @since 1.0
 */
public class RedisCacheManager implements ICacheManager {

    private RedisTemplate<String, Object> redisTemplate;

    private Logger logger = LoggerUtils.getLogger(RedisCacheManager.class);

    private String redisNamespace = "";

    public RedisCacheManager(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.redisNamespace = SystemConfig.getInstance().getValue("redis.namespace", "");
    }

    @Override
    public RedisCache getCache(String name, Long expiry) throws CacheException {
        name = handleName(name);
        LoggerUtils.debug(logger, "正在从redis获取缓存数据,key为:[{}]...", name);
        Map cacheMap = redisTemplate.opsForHash().entries(name);
        //LoggerUtils.debug(logger, "从redis获取缓存数据为:[{}]...", cacheMap);
        if(null == cacheMap) {
            cacheMap = new HashMap<>();
            redisTemplate.opsForHash().putAll(name, cacheMap);
            if(null != expiry && expiry > 0) {
                redisTemplate.expire(name, expiry, TimeUnit.MILLISECONDS);
            }
            return new RedisCache(redisTemplate, name);
        } else {
            return new RedisCache(redisTemplate, name);
        }
    }

    @Override
    public RedisCache getCacheIfNotAbsent(String name) throws CacheException {
        name = handleName(name);
        LoggerUtils.debug(logger, "正在从redis获取缓存数据,key为:[{}]...", name);
        Map cacheMap = redisTemplate.opsForHash().entries(name);
        //LoggerUtils.debug(logger, "从redis获取缓存数据为:[{}]...", cacheMap);
        if(null == cacheMap) {
            return null;
        } else {
            return new RedisCache(redisTemplate, name);
        }
    }

    @Override
    public RedisCache getCache(String name) throws CacheException {
        name = handleName(name);
        return getCache(name, 0L);
    }

    @Override
    public void destroy() throws CacheException {
        throw new UnsupportedOperationException("不支持该操作");
    }

    @Override
    public void remove(String name) {
        if(StringUtils.isEmpty(name)) {
            return;
        }
        name = handleName(name);
        redisTemplate.delete(name);
    }

    private String handleName(String name) {
        if(StringUtils.isNotEmpty(this.redisNamespace) && !name.startsWith(this.redisNamespace)) {
            return this.redisNamespace + name;
        } else {
            return name;
        }
    }
}
