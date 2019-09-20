package work.icql.api.common.util;

import org.springframework.cache.Cache;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author icql
 * @version 1.0
 * @date 2019/6/6 14:54
 * @Title CountLimiterUtils
 * @Description CountLimiterUtils
 */
public class CountLimiterUtils {

    /**
     * 执行方法回调接口
     */
    public interface Callback {

        /**
         * 执行方法
         *
         * @return
         * @throws RuntimeException
         */
        Object handle() throws RuntimeException;
    }

    private CountLimiterUtils() {
    }

    /**
     * caffeine缓存的limit限制器
     *
     * @param cacheTemplate
     * @param key
     * @param limitCount
     * @param duration
     * @param timeUnit
     * @param exceptionMsg
     * @param callback
     * @return
     */
    public static Object caffeineLimit(Cache cacheTemplate,
                                       String key,
                                       int limitCount,
                                       long duration,
                                       TimeUnit timeUnit,
                                       String exceptionMsg,
                                       Callback callback) {
        Object result;
        long currentTimeMillis = System.currentTimeMillis();

        Cache.ValueWrapper wrapper = cacheTemplate.get(key);

        List<Long> timeList;
        if (wrapper == null) {
            timeList = new ArrayList<>(limitCount);
            cacheTemplate.put(key, timeList);
        } else {
            timeList = (List) wrapper.get();
        }

        if (timeList.size() == limitCount) {
            long differTimeMillis = currentTimeMillis - timeList.get(0);
            if (differTimeMillis < timeUnit.toMillis(duration)) {
                throw new RuntimeException(exceptionMsg);
            }
        }

        try {
            result = callback.handle();
        } catch (RuntimeException e) {
            if (timeList.size() == limitCount) {
                timeList.remove(0);
            }
            timeList.add(currentTimeMillis);
            throw e;
        }
        return result;
    }

    ///**
    // * redis缓存的limit限制器
    // *
    // * @param redisTemplate
    // * @param key
    // * @param limitCount
    // * @param duration
    // * @param timeUnit
    // * @param exceptionMsg
    // * @param callback
    // * @return
    // */
    //public static Object redisLimit(RedisTemplate redisTemplate,
    //                                String key,
    //                                int limitCount,
    //                                long duration,
    //                                TimeUnit timeUnit,
    //                                String exceptionMsg,
    //                                Callback callback) {
    //    Object result;
    //    long currentTimeMillis = System.currentTimeMillis();
    //    Long size = redisTemplate.opsForList().size(key);
    //
    //    if (size == limitCount) {
    //        long differTimeMillis = currentTimeMillis - (long) redisTemplate.opsForList().index(key, 0);
    //        if (differTimeMillis < timeUnit.toMillis(duration)) {
    //            throw new RuntimeException(exceptionMsg);
    //        }
    //    }
    //
    //    try {
    //        result = callback.handle();
    //    } catch (RuntimeException e) {
    //        if (size == limitCount) {
    //            redisTemplate.opsForList().leftPop(key);
    //        }
    //        redisTemplate.opsForList().rightPush(key, currentTimeMillis);
    //        //设置过期时间
    //        redisTemplate.expire(key, duration, timeUnit);
    //        throw e;
    //    }
    //    return result;
    //}
}
