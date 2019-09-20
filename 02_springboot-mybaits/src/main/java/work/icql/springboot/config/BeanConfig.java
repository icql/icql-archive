package work.icql.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import work.icql.springboot.common.util.IdWorker;

/**
 * @author icql
 * @version 1.0
 * @date 2019/4/4 15:10
 * @Title BeanConfig
 * @Description BeanConfig
 */
@Configuration
public class BeanConfig {
    @Bean
    public IdWorker idWorker(){
        return new IdWorker();
    }
}
