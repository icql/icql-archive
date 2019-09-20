package work.icql.springboot.common.interceptor;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Statement;
import java.util.List;
import java.util.Properties;

/**
 * @author icql
 * @version 1.0
 * @date 2018/11/21 16:20
 * @Title MyBatisSqlCostInterceptor
 * @Description MyBatisSqlCostInterceptor
 */
@Intercepts(value = {
        @Signature(type = StatementHandler.class, method = "query", args = {Statement.class, ResultHandler.class}),
        @Signature(type = StatementHandler.class, method = "update", args = {Statement.class}),
        @Signature(type = StatementHandler.class, method = "batch", args = {Statement.class})})
public class MyBatisSqlCostInterceptor implements Interceptor {

    private final static Logger logger = LoggerFactory.getLogger(MyBatisSqlCostInterceptor.class);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object target = invocation.getTarget();


        long startTime = System.currentTimeMillis();
        StatementHandler statementHandler = (StatementHandler) target;
        try {
            return invocation.proceed();
        } finally {
            long endTime = System.currentTimeMillis();
            long sqlCost = endTime - startTime;
            BoundSql boundSql = statementHandler.getBoundSql();
            String sql = boundSql.getSql();
            //参数
            Object parameterObject = boundSql.getParameterObject();
            //参数映射
            List<ParameterMapping> parameterMappingList = boundSql.getParameterMappings();

            // 格式化Sql语句，去除换行符，替换参数
            //sql = formatSql(sql, parameterObject, parameterMappingList);
            sql = sql.replaceAll("[\\s\n ]+", " ");
            logger.info("SQL：执行耗时" + sqlCost + "ms [" + sql + "]");
        }
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }
}
