package work.icql.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import work.icql.api.common.result.Result;
import work.icql.api.pojo.dto.UserDTO;
import work.icql.api.service.UserService;

import javax.validation.constraints.NotNull;

/**
 * @author icql
 * @version 1.0
 * @date 2018/12/3 17:04
 * @Title UserController
 * @Description UserController
 */
@Api("User接口文档")
@Controller
@RequestMapping("user")
@Validated
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String index() {
        return "user/index";
    }

    @GetMapping("register")
    public String registerPage() {
        return "user/register";
    }

    @GetMapping("update")
    public String updatePage() {
        return "user/update";
    }

    @GetMapping("reactive")
    public String reactivePage() {
        return "user/reactive";
    }

    @GetMapping("getpwd")
    public String getpwdPage() {
        return "user/getpwd";
    }

    @GetMapping("close")
    public String close() {
        return "user/close";
    }

    //region api

    @ApiOperation("激活")
    @GetMapping("active")
    @ResponseBody
    public Result active(@RequestParam(name = "code") @NotNull String code) {
        userService.active(code);
        return Result.success();
    }

    @ApiOperation("重新激活")
    @PostMapping("reactive")
    @ResponseBody
    public Result reactive(@RequestBody UserDTO userDto) {
        userService.reactive(userDto);
        return Result.success();
    }

    @ApiOperation("找回密码")
    @PostMapping("getpwd")
    @ResponseBody
    public Result getPwd(@RequestBody UserDTO userDto) {
        userService.getPwd(userDto);
        return Result.success();
    }

    @ApiOperation("重置密码")
    @GetMapping("resetpwd")
    @ResponseBody
    public Result resetPwd(@RequestParam(name = "code") @NotNull String code) {
        userService.resetPwd(code);
        return Result.success();
    }


    @ApiOperation("注册")
    @PostMapping
    @ResponseBody
    public Result register(@RequestBody @Validated UserDTO userDto) {
        userService.register(userDto);
        return Result.success();
    }

    @ApiOperation("更新/修改密码")
    @PostMapping("update")
    @ResponseBody
    public Result update(@RequestBody UserDTO userDto) {
        userService.update(userDto);
        return Result.success();
    }

    @ApiOperation("关闭账户")
    @PostMapping("close")
    @ResponseBody
    public Result close(@RequestBody UserDTO userDto) {
        userService.close(userDto);
        return Result.success();
    }

    //endregion
}
