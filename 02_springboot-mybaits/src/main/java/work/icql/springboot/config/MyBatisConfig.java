package work.icql.springboot.config;

import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import work.icql.springboot.common.interceptor.MyBatisSqlCostInterceptor;

import javax.sql.DataSource;

/**
 * @author icql
 * @version 1.0
 * @date 2019/3/30 13:55
 * @Title MyBatisConfig
 * @Description MyBatisConfig
 */
@Configuration
@MapperScan("work.icql.springboot.mapper*")
public class MyBatisConfig {

    @Value("${mybatis.mapper-locations}")
    private String mapperLocations;

    @Profile("dev")
    @Bean
    public MyBatisSqlCostInterceptor sqlCostInterceptor() {
        return new MyBatisSqlCostInterceptor();
    }

    @Bean
    public PageInterceptor pageInterceptor() {
        return new PageInterceptor();
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dynamicDataSource") DataSource dataSource, PageInterceptor pageInterceptor, MyBatisSqlCostInterceptor sqlCostInterceptor) throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocations));
        sqlSessionFactory.setPlugins(new Interceptor[]{pageInterceptor, sqlCostInterceptor});
        return sqlSessionFactory.getObject();
    }
}
