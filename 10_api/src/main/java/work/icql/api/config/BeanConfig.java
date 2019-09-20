package work.icql.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import work.icql.api.common.util.IdWorker;

/**
 * @author icql
 * @version 1.0
 * @date 2018/12/4 15:10
 * @Title BeanConfig
 * @Description BeanConfig
 */
@Configuration
public class BeanConfig {
    @Bean
    public IdWorker idWorker(){
        return new IdWorker();
    }

    @Bean
    public BCryptPasswordEncoder bcryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
