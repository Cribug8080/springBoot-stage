package com.finup.test.config;


import com.github.benmanes.caffeine.cache.CaffeineSpec;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class CatchConfig {

    /**
     * 登录过期时间
     * @return
     */
    @Bean
    @Primary
    public CacheManager catchManager(){
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
        caffeineCacheManager.setCaffeineSpec(CaffeineSpec.parse("expireAfterAccess=20m"));
        return caffeineCacheManager;
    }
}
