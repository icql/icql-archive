package work.icql.api.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author icql
 * @version 1.0
 * @date 2019/3/31 12:54
 * @Title FailedRetry
 * @Description FailedRetry
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface FailedRetry {
    /**
     * 发生什么异常时需要重试
     * @return
     */
    Class[] exceptionClazz();

    /**
     * 最大重试次数
     *
     * @return
     */
    int maxRetries();
}
