package work.icql.springboot.config;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.*;
import org.springframework.util.ReflectionUtils;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author icql
 * @version 1.0
 * @date 2018/11/22 10:25
 * @Title CacheConfig
 * @Description CacheConfig
 */
@Configuration
@EnableCaching
public class CacheConfig extends CachingConfigurerSupport {

    @Value("${spring.cache.redis-default-expire}")
    private Long redisDefaultExpire;

    @Bean
    public CacheManager redisCacheManager(ApplicationContext applicationContext, RedisConnectionFactory factory) {
        RedisSerializationContext.SerializationPair<String> keyPair = RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer());

        //jackson
        //RedisSerializationContext.SerializationPair valuePair = RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer());

        //fastjson
        RedisSerializationContext.SerializationPair valuePair = RedisSerializationContext.SerializationPair.fromSerializer(new GenericFastJsonRedisSerializer());

        return new RedisCacheManager(
                RedisCacheWriter.nonLockingRedisCacheWriter(factory),
                //默认过期时间
                RedisCacheConfiguration.defaultCacheConfig()
                        .serializeKeysWith(keyPair)
                        .serializeValuesWith(valuePair)
                        .entryTtl(Duration.ofSeconds(redisDefaultExpire + new Random().nextInt(10))),
                //@Cacheable 自定义过期时间
                getRedisCacheConfigMap(applicationContext,keyPair,valuePair)
        );
    }


    /**
     * 自定义过期时间：@Cacheable，cacheNames={cacheName#过期时间}
     *
     * @param applicationContext
     * @return
     */
    private Map<String, RedisCacheConfiguration> getRedisCacheConfigMap(ApplicationContext applicationContext,RedisSerializationContext.SerializationPair<String> keyPair,RedisSerializationContext.SerializationPair valuePair) {
        Map<String, RedisCacheConfiguration> redisCacheConfigurationMap = new HashMap<>();

        String[] beanNames = applicationContext.getBeanNamesForType(Object.class);
        for (String beanName : beanNames) {
            final Class clazz = applicationContext.getType(beanName);
            ReflectionUtils.doWithMethods(clazz, method -> {
                ReflectionUtils.makeAccessible(method);
                Cacheable cacheable = AnnotationUtils.findAnnotation(method, Cacheable.class);
                if (cacheable != null) {
                    RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig();
                    String[] strings = cacheable.cacheNames();
                    for (String item : strings) {
                        if (item.contains("#")) {
                            String[] split = item.split("#");
                            redisCacheConfigurationMap.put(item,
                                    defaultCacheConfig
                                            .serializeKeysWith(keyPair)
                                            .serializeValuesWith(valuePair)
                                            .entryTtl(Duration.ofSeconds(Long.valueOf(split[1]) + new Random().nextInt(10))));
                        }
                    }
                }

            });
        }
        return redisCacheConfigurationMap;
    }

    /**
     * 缓存出现故障后查询数据库
     *
     * @return
     */
    @Bean
    @Override
    public CacheErrorHandler errorHandler() {
        CacheErrorHandler cacheErrorHandler = new CacheErrorHandler() {
            @Override
            public void handleCacheGetError(RuntimeException e, Cache cache, Object key) {
                //通知管理员
            }

            @Override
            public void handleCachePutError(RuntimeException e, Cache cache, Object key, Object value) {
                //通知管理员
            }

            @Override
            public void handleCacheEvictError(RuntimeException e, Cache cache, Object key) {
                //通知管理员
            }

            @Override
            public void handleCacheClearError(RuntimeException e, Cache cache) {
                //通知管理员
            }
        };
        return cacheErrorHandler;
    }

}
