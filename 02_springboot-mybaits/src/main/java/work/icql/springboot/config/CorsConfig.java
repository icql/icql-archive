package work.icql.springboot.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author icql
 * @version 1.0
 * @date 2018/11/21 13:55
 * @Title CorsConf：更细粒度的控制,可在控制器或方法上@CrossOrigin
 * @Description CorsConf
 */
@Configuration
public class CorsConfig {
    //跨域设置
    @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration conf = new CorsConfiguration();
        conf.setAllowCredentials(true);
        conf.addAllowedOrigin("*");
        conf.addAllowedHeader("*");
        conf.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", conf);
        FilterRegistrationBean bean = new FilterRegistrationBean<>(new CorsFilter(source));

        bean.setOrder(0);
        return bean;
    }
}
