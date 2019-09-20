package work.icql.api.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author icql
 * @version 1.0
 * @date 2018/11/22 10:25
 * @Title CacheConfig
 * @Description CacheConfig
 */
@Configuration
@EnableCaching
public class CacheConfig {

    @Value("${spring.cache.caffeine.initialCapacity}")
    private Integer initialCapacity;

    @Value("${spring.cache.caffeine.maximumSize}")
    private Integer maximumSize;

    @Value("${spring.cache.caffeine.expireAfterWrite}")
    private Integer expireAfterWrite;

    @Value("${spring.cache.caffeine.cacheTemplateExpireAfterWrite}")
    private Integer cacheTemplateExpireAfterWrite;


    @Bean
    @Primary
    public CacheManager caffeineCacheManager(ApplicationContext applicationContext) {
        List<CaffeineCache> caches = new ArrayList<>();

        //用来全局手动设置缓存cacheTemplate
        caches.add(new CaffeineCache("cacheTemplate4expired",
                Caffeine.newBuilder().recordStats()
                        .expireAfterWrite(cacheTemplateExpireAfterWrite, TimeUnit.SECONDS)
                        .initialCapacity(initialCapacity)
                        .maximumSize(maximumSize)
                        .build()));
        caches.add(new CaffeineCache("cacheTemplate",
                Caffeine.newBuilder().recordStats()
                        .initialCapacity(initialCapacity)
                        .maximumSize(maximumSize)
                        .build()));

        //region 处理@Cacheable
        String[] beanNames = applicationContext.getBeanNamesForType(Object.class);
        for (String beanName : beanNames) {
            final Class clazz = applicationContext.getType(beanName);
            //只处理在方法上的@Cacheable注解
            ReflectionUtils.doWithMethods(clazz, method -> {
                ReflectionUtils.makeAccessible(method);
                Cacheable cacheable = AnnotationUtils.findAnnotation(method, Cacheable.class);
                if (cacheable != null) {
                    String[] strings = cacheable.cacheNames();
                    for (String item : strings) {
                        if (item.contains("#")) {
                            //自定义：过期时间：@Cacheable，cacheNames={cacheName#过期时间}
                            String[] split = item.split("#");
                            caches.add(new CaffeineCache(item,
                                    Caffeine.newBuilder().recordStats()
                                            .expireAfterWrite(Long.valueOf(split[1]), TimeUnit.SECONDS)
                                            .initialCapacity(initialCapacity)
                                            .maximumSize(maximumSize)
                                            .build()));
                        } else {
                            //默认设置
                            caches.add(new CaffeineCache(item,
                                    Caffeine.newBuilder().recordStats()
                                            .expireAfterWrite(expireAfterWrite, TimeUnit.SECONDS)
                                            .initialCapacity(initialCapacity)
                                            .maximumSize(maximumSize)
                                            .build()));
                        }
                    }
                }

            });
        }
        //endregion

        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(caches);
        return cacheManager;
    }

    @Bean
    public Cache cacheTemplate4expired(CacheManager cacheManager) {
        return cacheManager.getCache("cacheTemplate4expired");
    }

    @Bean
    public Cache cacheTemplate(CacheManager cacheManager) {
        return cacheManager.getCache("cacheTemplate");
    }
}
