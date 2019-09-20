package work.icql.api.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.datasource.lookup.DataSourceLookupFailureException;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

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

    @Pointcut("@within(work.icql.api.common.annotation.DataSource) || @annotation(work.icql.api.common.annotation.DataSource)")
    private void dataSourcePointcut() {
    }

    @Before("dataSourcePointcut()")
    public void dataSourceBefore(JoinPoint point) throws Exception {
        Class<?> clazz = point.getTarget().getClass();
        String methodName = point.getSignature().getName();
        Class[] argClass = ((MethodSignature) point.getSignature()).getParameterTypes();
        Method method = clazz.getMethod(methodName, argClass);
        //如果方法上有注解，则使用方法上注解的数据源；否则使用类型上的；若都没有则使用默认数据源
        if (method.isAnnotationPresent(work.icql.api.common.annotation.DataSource.class)) {
            setDataSource(clazz, methodName, method.getAnnotation(work.icql.api.common.annotation.DataSource.class));
        } else if (clazz.isAnnotationPresent(work.icql.api.common.annotation.DataSource.class)) {
            setDataSource(clazz, methodName, clazz.getAnnotation(work.icql.api.common.annotation.DataSource.class));
        }
    }

    @After("dataSourcePointcut()")
    public void dataSourceAfter() {
        DataSourceConfig.DATA_SOURCE_CONTEXT_HOLDER.remove();
    }

    private void setDataSource(Class<?> clazz, String methodName, work.icql.api.common.annotation.DataSource annotation) {
        //取出注解中的数据源名，并设置数据源
        String value = annotation.value();
        if (!DataSourceConfig.dataSourceBeanNames.contains(value)) {
            String msg = clazz + "." + methodName + "() 配置的数据源名称不正确";
            throw new DataSourceLookupFailureException(msg);
        }
        DataSourceConfig.DATA_SOURCE_CONTEXT_HOLDER.set(value);
    }
}
