package work.icql.ssm.interceptor;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import work.icql.ssm.pojo.TbShiroUser;
import work.icql.ssm.pojo.VwShiroAuth;
import work.icql.ssm.service.ShiroService;

import java.util.List;

/**
 * @author icql
 * @version 1.0
 * @date 2018/11/13 14:52
 * @Title IcqlRealm
 * @Description IcqlRealm
 */
public class IcqlRealm extends AuthorizingRealm {

    @Autowired
    private ShiroService shiroService;

    /**
     * 认证方法
     */
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();// 从令牌中获得用户名

        TbShiroUser shiroUser = shiroService.getShiroUserByUsername(username);
        if (null == shiroUser) {
            // 用户名不存在
            return null;
        } else {
            // 用户名存在
            String password = shiroUser.getPassword();// 获得数据库中存储的密码
            // 创建简单认证信息对象
            /***
             * 参数一：签名，程序可以在任意位置获取当前放入的对象
             * 参数二：从数据库中查询出的密码
             * 参数三：当前realm的名称
             */
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(shiroUser,
                    password, this.getClass().getSimpleName());
            return info;//返回给安全管理器，由安全管理器负责比对数据库中查询出的密码和页面提交的密码
        }
    }

    /**
     * 授权方法（配置了缓存，这里只会初始化一次）
     */
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //根据当前登录用户查询其对应的权限，授权
        TbShiroUser user = (TbShiroUser) principals.getPrimaryPrincipal();

        List<VwShiroAuth> list = shiroService.getShiroFuncByUserName(user.getUsername());
        for (VwShiroAuth function : list) {
            info.addRole(function.getRolecode());
            info.addStringPermission(function.getFunccode());
        }
        return info;
    }

}
