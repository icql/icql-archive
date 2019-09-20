package work.icql.springboot.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerMapping;
import work.icql.springboot.common.result.Result;
import work.icql.springboot.common.result.ResultCode;

import java.util.List;

/**
 * @author icql
 * @version 1.0
 * @date 2019/6/18 9:32
 * @Title ShiroController
 * @Description ShiroController
 */
@Api("Shiro test")
@RestController
@RequestMapping("shiro")
public class ShiroController {

    @ApiOperation("test登录界面")
    @GetMapping("/loginPage")
    public Object loginPage() {
        return Result.success("登录界面");
    }

    @ApiOperation("test登录界面")
    @GetMapping("/successPage")
    public Object successPage() {
        return Result.success("登录成功界面");
    }


    @ApiOperation("test未授权页面")
    @GetMapping("/deniedPage")
    public Object deniedPage() {
        return Result.success();
    }

    @ApiOperation("test登录请求")
    @GetMapping("/login")
    public Object login(String username, String password) {
        //获得当前用户对象，状态为“未认证”
        Subject subject = SecurityUtils.getSubject();

        //构造一个用户名密码令牌
        AuthenticationToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
        } catch (Exception e) {
            e.printStackTrace();
            //登录错误，跳转到登录界面
            return Result.failure(ResultCode.AUTH_IS_ERROR);
        }
        //获取认证信息对象中存储的User对象
        Object principal = subject.getPrincipal();

        //根据权限，指定返回数据
        return Result.success();
    }

    @ApiOperation("test test1")
    @GetMapping("/test1")
    public Object test1() {
        return Result.success();
    }

    @ApiOperation("test test2")
    @GetMapping("/test2")
    public Object test2() {
        return Result.success();
    }

    @ApiOperation("test test3")
    @GetMapping("/test3")
    public Object test3() {
        return Result.success();
    }

}
