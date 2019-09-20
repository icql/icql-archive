package work.icql.ithome.user.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author icql
 * @version 1.0
 * @date 2019/1/29 11:35
 * @Title SecurityConfig
 * @Description SecurityConfig
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //authorizeRequests 请求授权开始
        //anyRequest 任何请求
        //authenticated 需要认证后才能访问
        //antMatchers 拦截的路径
        //permitAll 需要的权限
        //and().csrf().disable() 使csrf拦截失效
        http
            .authorizeRequests()
            .anyRequest()
            .authenticated()
            .antMatchers("/**")
            .permitAll()
            .and().csrf().disable();
    }
}
