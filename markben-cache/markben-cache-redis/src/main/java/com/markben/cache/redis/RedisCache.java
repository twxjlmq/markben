package com.markben.cache.redis;

import com.markben.cache.ICache;
import com.markben.common.logger.Logger;
import com.markben.common.utils.LoggerUtils;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * redis缓存对象
 * @author 乌草坡
 * @since 1.0
 */
public class RedisCache implements ICache<String, Object> {

    private String mainKey;

    private RedisTemplate<String, Object> redisTemplate;

    private static final Logger logger = LoggerUtils.getLogger(RedisCache.class);

    public RedisCache(RedisTemplate<String, Object> redisTemplate, String mainKey) {
        this.redisTemplate = redisTemplate;
        this.mainKey = mainKey;
    }

    @Override
    public Object get(String key) {
        Object object = this.redisTemplate.opsForHash().get(mainKey, key);
        LoggerUtils.debug(logger, "正在从redis获取[{}]的缓存...", key);
        //LoggerUtils.debug(logger, "正在从redis获取[{}]的缓存数据[{}]...", key, object);
        return object;
    }

    @Override
    public Object put(String key, Object value) {
        LoggerUtils.debug(logger, "正在从redis设置[{}]的缓存...", key);
        //LoggerUtils.debug(logger, "正在从redis设置[{}]的缓存数据[{}]...", key, value);
        this.redisTemplate.opsForHash().put(mainKey, key, value);
        return value;
    }

    @Override
    public Object remove(String key) {
        Object value = redisTemplate.opsForHash().delete(key);
        return value;
    }

    @Override
    public void clear() {
        redisTemplate.delete(mainKey);
    }
}
