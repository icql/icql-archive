package work.icql.springboot.common.interceptor;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import work.icql.springboot.common.annotation.MBStrFilter;

import java.lang.reflect.Field;
import java.util.Properties;

/**
 * @author icql
 * @version 1.0
 * @date 2018/11/23 11:15
 * @Title MyBatisStrFilterInterceptor
 * @Description MyBatisStrFilterInterceptor
 */
@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})})
public class MyBatisStrFilterInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        //MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        //SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
        Object parameterObject = invocation.getArgs()[1];

        if (parameterObject != null) {
            Field[] declaredFields = parameterObject.getClass().getDeclaredFields();
            for (Field field : declaredFields) {
                field.setAccessible(true);
                MBStrFilter annotation = field.getAnnotation(MBStrFilter.class);
                if (annotation != null) {
                    String[] filters = annotation.value();
                    if (filters != null && filters.length != 0) {
                        Object val = field.get(parameterObject);
                        if (val != null && val instanceof String) {
                            for (String item : filters) {
                                val = val.toString().replace(item, "");
                            }
                            field.set(parameterObject, val);
                        }
                    }
                }
            }
        }

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }
}
