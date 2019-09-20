package work.icql.springboot.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.datasource.lookup.DataSourceLookupFailureException;
import org.springframework.stereotype.Component;
import work.icql.springboot.common.annotation.FailedRetry;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @author icql
 * @version 1.0
 * @date 2018/11/30 14:18
 * @Title AopConfig
 * @Description AopConfig
 */
@Slf4j
@Component
@Aspect
@Order(0)
public class AopConfig {

    /**
     * 注意：目前只支持 jdk动态代理，即使用的范围只有 有接口的实现类里边
     */

    /**
     * execution() 方法模式串，通用
     *
     * @within 有注解标记的类型
     * @annotation 有注解标记的方法
     */

    @Pointcut("@within(work.icql.springboot.common.annotation.DataSource) || @annotation(work.icql.springboot.common.annotation.DataSource)")
    private void dataSourcePointcut() {
    }

    @Before("dataSourcePointcut()")
    public void dataSourceBefore(JoinPoint point) throws Exception {
        Class<?> clazz = point.getTarget().getClass();
        String methodName = point.getSignature().getName();
        Class[] argClass = ((MethodSignature) point.getSignature()).getParameterTypes();
        Method method = clazz.getMethod(methodName, argClass);
        //如果方法上有注解，则使用方法上注解的数据源；否则使用类型上的；若都没有则使用默认数据源
        if (method.isAnnotationPresent(work.icql.springboot.common.annotation.DataSource.class)) {
            setDataSource(clazz, methodName, method.getAnnotation(work.icql.springboot.common.annotation.DataSource.class));
        } else if (clazz.isAnnotationPresent(work.icql.springboot.common.annotation.DataSource.class)) {
            setDataSource(clazz, methodName, clazz.getAnnotation(work.icql.springboot.common.annotation.DataSource.class));
        }
    }

    @After("dataSourcePointcut()")
    public void dataSourceAfter() {
        DataSourceConfig.dataSourceContextHolder.remove();
    }

    private void setDataSource(Class<?> clazz, String methodName, work.icql.springboot.common.annotation.DataSource annotation) {
        //取出注解中的数据源名，并设置数据源
        String value = annotation.value();
        if (!DataSourceConfig.dataSourceBeanNames.contains(value)) {
            String msg = clazz + "." + methodName + "() 配置的数据源名称不正确";
            throw new DataSourceLookupFailureException(msg);
        }
        DataSourceConfig.dataSourceContextHolder.set(value);
    }

    /*-----------------------------------------------------------------------------------------------------------------*/

    @Pointcut("@annotation(work.icql.springboot.common.annotation.FailedRetry)")
    private void failedRetryPointcut() {
    }

    @Around("failedRetryPointcut()")
    public Object failedRetryAround(ProceedingJoinPoint point) throws Throwable {
        Class<?> clazz = point.getTarget().getClass();
        String methodName = point.getSignature().getName();
        Class[] argClass = ((MethodSignature) point.getSignature()).getParameterTypes();
        Method method = clazz.getMethod(methodName, argClass);
        FailedRetry annotation = method.getAnnotation(FailedRetry.class);
        Class[] classes = annotation.exceptionClazz();
        int maxRetries = annotation.maxRetries();
        int durationSeconds = annotation.durationSeconds();

        int retryCount = 0;
        while (retryCount <= maxRetries) {
            try {
                return point.proceed();
            } catch (Throwable throwable) {
                if (Arrays.asList(classes).contains(throwable.getClass())) {
                    log.info("失败重试：" + retryCount);
                    retryCount++;
                    if (retryCount > maxRetries) {
                        throw throwable;
                    }
                } else {
                    throw throwable;
                }
            }
            TimeUnit.SECONDS.sleep(durationSeconds);
        }
        return null;
    }
}
