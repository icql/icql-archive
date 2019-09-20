package work.icql.springboot.config;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author icql
 * @version 1.0
 * @date 2019/6/5 9:41
 * @Title ShiroConfig
 * @Description ShiroConfig
 */
@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shirFilter(DefaultWebSecurityManager securityManager) {

        //获取所有注册的url，以便实现可视化动态权限控制
        //在权限管理请求中 处理所有url,删除失效url，增加新的url
        //,@Qualifier("requestMappingHandlerMapping") AbstractHandlerMethodMapping handlerMethodMapping
        //Map<RequestMappingInfo, HandlerMethod> handlerMethods = handlerMethodMapping.getHandlerMethods();


        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // setLoginUrl 如果不设置值，默认会自动寻找Web工程根目录下的"/login.jsp"页面 或 "/login" 映射
        shiroFilterFactoryBean.setLoginUrl("/shiro/loginPage");
        // 设置无权限时跳转的 url;
        shiroFilterFactoryBean.setUnauthorizedUrl("/shiro/deniedPage");
        //设置登录成功后跳转的url
        shiroFilterFactoryBean.setSuccessUrl("/shiro/successPage");

        // 设置拦截器
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();

        filterChainDefinitionMap.put("/**", "anon");

        ////开放登陆接口
        //filterChainDefinitionMap.put("/shiro/login", "anon");

        ////开放权限
        //filterChainDefinitionMap.put("/shiro/test1", "anon");
        //
        //
        ////动态权限在这里查库设置，当权限有变动时，应当刷新过滤链中的数据，实现实时生效
        //
        //
        ////需要角色权限 “user”
        //filterChainDefinitionMap.put("/shiro/test2", "roles[user]");
        //
        ////需要角色权限 “admin”
        //filterChainDefinitionMap.put("/shiro/test3", "roles[admin]");
        //
        //
        ////其余接口一律拦截
        //filterChainDefinitionMap.put("/**", "authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        System.out.println("Shiro拦截器工厂类注入成功");
        return shiroFilterFactoryBean;
    }

    /**
     * 注入 securityManager
     */
    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置realm
        securityManager.setRealm(customRealm());
        //设置权限认证缓存
        securityManager.setCacheManager(memoryConstrainedCacheManager());

        return securityManager;
    }

    @Bean
    public CustomRealm customRealm() {
        return new CustomRealm();
    }

    @Bean
    public MemoryConstrainedCacheManager memoryConstrainedCacheManager() {
        return new MemoryConstrainedCacheManager();
    }

    class CustomRealm extends AuthorizingRealm {

        /**
         * 获取身份验证信息
         * Shiro中，最终是通过 Realm 来获取应用程序中的用户、角色及权限信息的。
         *
         * @param authenticationToken 用户身份信息 token
         * @return 返回封装了用户信息的 AuthenticationInfo 实例
         */
        @Override
        protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
            System.out.println("————身份认证方法————");
            UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
            // 从数据库获取对应用户名的密码
            String password = "123";
            if (null == password) {
                throw new AccountException("用户名不正确");
            } else if (!password.equals(new String((char[]) token.getCredentials()))) {
                throw new AccountException("密码不正确");
            }
            return new SimpleAuthenticationInfo(token.getPrincipal(), password, getName());
        }

        /**
         * 获取授权信息
         *
         * @param principalCollection
         * @return
         */
        @Override
        protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
            System.out.println("————权限认证————");
            String username = (String) SecurityUtils.getSubject().getPrincipal();
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

            //获得该用户角色
            String role = "admin";

            Set<String> set = new HashSet<>();
            //需要将 role 封装到 Set 作为 info.setRoles() 的参数
            set.add(role);
            //设置该用户拥有的角色
            info.setRoles(set);
            return info;
        }
    }
}
