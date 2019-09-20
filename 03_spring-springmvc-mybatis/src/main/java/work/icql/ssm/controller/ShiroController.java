package work.icql.ssm.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import work.icql.ssm.pojo.TbShiroUser;

/**
 * @author icql
 * @version 1.0
 * @date 2018/11/1 17:31
 * @Title ShiroController
 * @Description ShiroController
 */
@Controller
@RequestMapping("/shiro")
public class ShiroController {

    @RequestMapping("/{page}")
    public String page(@PathVariable String page) {
        page = "shiro/" + page;
        return page;
    }

    @RequestMapping("/api/login")
    public String login(TbShiroUser user){
        //获得当前用户对象
        Subject subject = SecurityUtils.getSubject();//状态为“未认证”
        //构造一个用户名密码令牌
        AuthenticationToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        try{
            subject.login(token);
        }catch (UnknownAccountException e) {
            e.printStackTrace();
            //设置错误信息
            return "redirect:/shiro/login";
        }catch (Exception e) {
            e.printStackTrace();
            //设置错误信息
            return "redirect:/shiro/login";
        }
        //获取认证信息对象中存储的User对象
        TbShiroUser shiroUser = (TbShiroUser) subject.getPrincipal();
        return "redirect:/shiro/index";
    }

    @RequiresPermissions("func1")
    @RequestMapping("/api/testFunc1")
    @ResponseBody
    public String testFunc1(){
        return "api/testFunc1";
    }

    @RequestMapping("/api/testFunc2")
    @ResponseBody
    public String testFunc2(){
        return "api/testFunc2";
    }
}
