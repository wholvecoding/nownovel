package com.wolvescoding.nownovel.core.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.wolvescoding.nownovel.core.constant.CacheConsts;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import java.time.Duration;
import java.util.*;

public class CacheConfig  {
    @Bean
    public CacheManager caffeineCacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        List<CaffeineCache> caches = new ArrayList<>(CacheConsts.CacheEnum.values().length);
        for(CacheConsts.CacheEnum c: CacheConsts.CacheEnum.values()){
            if(c.isLocal()){
                Caffeine<Object, Object> caffine = Caffeine.newBuilder().recordStats().maximumSize(c.getMaxSize());
                if(c.getTtl() > 0){
                     caffine.expireAfterWrite(Duration.ofSeconds(c.getTtl()));
                }
                caches.add(new CaffeineCache(c.getName(), caffine.build()));
            }
        }
        cacheManager.setCaches(caches);
        return cacheManager;
    }
    @Bean
    public CacheManager redisCacheManager(RedisConnectionFactory connectionFactory) {
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory);
        RedisCacheConfiguration defaultRedisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .disableCachingNullValues().prefixCacheNameWith(CacheConsts.REDIS_CACHE_PREFIX);
        Map<String, RedisCacheConfiguration> cacheMap = new LinkedHashMap<>(CacheConsts.CacheEnum.values().length);
        for (CacheConsts.CacheEnum c : CacheConsts.CacheEnum.values()) {
            if (c.isRemote()) {
                if (c.getTtl() > 0) {
                    cacheMap.put(c.getName(), RedisCacheConfiguration.defaultCacheConfig().disableCachingNullValues()
                            .prefixCacheNameWith(CacheConsts.REDIS_CACHE_PREFIX).entryTtl(Duration.ofSeconds(c.getTtl())));
                } else {
                    cacheMap.put(c.getName(), RedisCacheConfiguration.defaultCacheConfig().disableCachingNullValues()
                            .prefixCacheNameWith(CacheConsts.REDIS_CACHE_PREFIX));
                }
            }
        }

        RedisCacheManager redisCacheManager = new RedisCacheManager(redisCacheWriter, defaultRedisCacheConfiguration, cacheMap);
        redisCacheManager.setTransactionAware(true);
        redisCacheManager.initializeCaches();
        return redisCacheManager;
    }


}
