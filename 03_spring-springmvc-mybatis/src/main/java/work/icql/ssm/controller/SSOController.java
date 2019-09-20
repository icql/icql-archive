package work.icql.ssm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import work.icql.ssm.pojo.TbSSOUser;
import work.icql.ssm.service.SSOService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * sso-client
 * login:
 * ----登录检查(session或者redis等其他方式)
 * --------已登录：return
 * --------未登录：请求参数中验证 ticket 是否存在
 * ------------是：请求sso-listener(/validate,参数ticket)，验证 令牌ticket 是否有效
 * ----------------有效：sso登录通过
 * ----------------无效：删除url ticket参数后的字符串,重定向sso-listener(/login,参数service),return
 * ------------否：重定向sso-listener(/login,参数service),return
 * logout:
 * ----删除自身登录校验,重定向到sso-listener(/logout,参数service),return
 * *****************************************************************************
 * *****************************************************************************
 * sso-listener
 * login:(参数service)
 * ----验证全局票据 cookie[ICQLTGC] 是否存在
 * --------是：已经登录过,检查缓存是否存在此TGC
 * ------------是
 * ----------------根据参数service生成唯一令牌ST(ticket)
 * ----------------根据TGC(cookie[ICQLTGC]的值)从缓存中查询 用户信息
 * ----------------缓存[key:唯一令牌ST(ticket),用户信息],设置默认生效时间5分钟
 * ----------------更新 TGC 生效时间
 * ----------------service为空,重定向到/success,不为空重定向到 [service + "?ticket=" + 令牌ST(ticket)] ,return
 * ------------否：未登录,显示sso-server登录页面,传递参数service到页面
 * --------否：未登录,显示sso-server登录页面,传递参数service到页面
 * ----------------发送登录请求login4form(参数:service,username,password),验证用户密码是否正确
 * --------------------成功：将会返回令牌ST(ticket),重定向到 [service + "?ticket=" + 令牌ST(ticket)]
 * --------------------失败：停留在sso-server登录页面
 * login4form:(参数:service,username,password)
 * ----验证参数
 * --------未通过：
 * --------通过
 * ----数据库查询 用户信息user
 * --------未找到：return
 * --------找到
 * ----根据参数service,生成sso-client的唯一令牌ST(ticket)
 * ----根据参数sso-server的域名,生成sso-server的唯一票据TGC
 * ----缓存[key:唯一令牌ST(ticket),用户信息],设置默认生效时间5分钟
 * ----缓存[key:TGC,用户信息],保存登录用户的信息,设置默认生效时间60分钟
 * ----设置sso-server的浏览器cookie(key:ICQLTGC,value:TGC)
 * ----return 唯一令牌ST(ticket)
 * validate:(参数ticket)
 * ----根据ticket从缓存中查询 用户信息
 * --------找到：200,返回用户信息,从缓存中清除 ticket 相关信息(ST令牌只能使用一次)
 * --------未找到：204
 * logout:(参数service)
 * ----验证全局票据 cookie[ICQLTGC] 是否存在
 * --------存在：删除 缓存TGC,删除cookie[ICQLTGC]
 * --------不存在
 * --------service为空,重定向到/logout,不为空重定向到 service ,return
 * register4form:(参数)
 * ----注册页面未实现
 * register4check:(参数)
 * ----
 */


/**
 * @author icql
 * @version 1.0
 * @date 2018/11/1 16:56
 * @Title SSOController
 * @Description SSOController
 */
@Controller
@RequestMapping("/sso")
public class SSOController {
    @Autowired
    private SSOService ssoService;

    @RequestMapping("/login")
    public String login(HttpServletRequest request, Model model, String service) {
        return ssoService.login(request, model, service);
    }

    @RequestMapping("/login4form")
    @ResponseBody
    public Object login4form(HttpServletRequest request, HttpServletResponse response, String service, String username, String password) {
        return ssoService.login4form(request, response, service, username, password);
    }

    @RequestMapping("/validate")
    @ResponseBody
    public Object validate(String ticket) {
        return ssoService.validate(ticket);
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response, String service) {
        return ssoService.logout(request, response, service);
    }

    @RequestMapping("/register4form")
    @ResponseBody
    public Object register(TbSSOUser user) {
        return ssoService.register4form(user);
    }

    @RequestMapping("/register4check/{type}/{value}")
    @ResponseBody
    public Object checkRegisterInfo(@PathVariable int type, @PathVariable String value) {
        return ssoService.register4check(type, value);
    }
}
