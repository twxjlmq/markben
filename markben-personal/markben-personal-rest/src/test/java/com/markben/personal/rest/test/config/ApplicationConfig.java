package com.markben.personal.rest.test.config;

import com.markben.cache.ICacheManager;
import com.markben.cache.ehcache.EhCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 乌草坡
 * @since 0.0.1
 */
@Configuration
public class ApplicationConfig {

    @Bean
    public ICacheManager createCacheManager() {
        return new EhCacheManager();
    }

}
