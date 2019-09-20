package work.icql.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author icql
 * @version 1.0
 * @date 2018/11/29 10:53
 * @Title WebConf
 * @Description WebConf
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String apiUri = "/**";
        //请求头参数校验
        //registry.addInterceptor(headerParamsCheckInterceptor).addPathPatterns(apiUri);
        ////登录拦截
        //registry.addInterceptor(loginedAuthInterceptor).addPathPatterns(apiUri);
    }

}
