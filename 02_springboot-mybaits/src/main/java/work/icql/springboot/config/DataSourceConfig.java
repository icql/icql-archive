package work.icql.springboot.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author icql
 * @version 1.0
 * @date 2018/11/27 8:51
 * @Title DataSourceConf
 * @Description DataSourceConf
 */
@Configuration
public class DataSourceConfig {

    @PostConstruct
    public void init() {
        dataSourceBeanNames = new ArrayList<>(2);
        dataSourceBeanNames.add("db1");//第一个为默认数据源
        dataSourceBeanNames.add("db2");
    }

    @Bean("db1")
    @ConfigurationProperties(prefix = "spring.datasource.druid.db1")
    public DataSource db1() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean("db2")
    @ConfigurationProperties(prefix = "spring.datasource.druid.db2")
    public DataSource db2() {
        return DruidDataSourceBuilder.create().build();
    }

    /*----------------------------------------------请配置分割线以上内容-----------------------------------------------*/

    /**
     * 动态数据源
     *
     * @param applicationContext
     * @return
     */
    @Primary
    @Bean("dynamicDataSource")
    public DataSource dynamicDataSource(@Autowired ApplicationContext applicationContext) {
        Map<Object, Object> dsMap = new HashMap<>();
        for (String ds : dataSourceBeanNames) {
            dsMap.put(ds, applicationContext.getBean(ds));
        }
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        //配置多数据源
        dynamicDataSource.setTargetDataSources(dsMap);
        //配置默认数据源
        dynamicDataSource.setDefaultTargetDataSource(dsMap.get(dataSourceBeanNames.get(0)));
        return dynamicDataSource;
    }

    public static List<String> dataSourceBeanNames;

    public final static ThreadLocal<String> dataSourceContextHolder = new ThreadLocal<>();

    class DynamicDataSource extends AbstractRoutingDataSource {
        /**
         * 数据源切换逻辑
         * @return
         */
        @Override
        @Order(1)
        protected Object determineCurrentLookupKey() {
            String dataSourceBeanName = dataSourceContextHolder.get();
            dataSourceBeanName = dataSourceBeanName == null ? dataSourceBeanNames.get(0) : dataSourceBeanName;
            logger.info("数据源：" + dataSourceBeanName);
            return dataSourceBeanName;
        }
    }

    //不用配置事务管理器，会自动配置
    //@Bean
    //public PlatformTransactionManager transactionManager(@Qualifier("dynamicDataSource") DataSource dataSource) {
    //    return new DataSourceTransactionManager(dataSource);
    //}
}
