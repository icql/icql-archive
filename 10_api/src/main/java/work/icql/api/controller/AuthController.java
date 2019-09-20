package work.icql.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import work.icql.api.common.result.Result;
import work.icql.api.service.AuthService;

import javax.validation.constraints.NotNull;

/**
 * @author icql
 * @version 1.0
 * @date 2018/12/3 17:04
 * @Title AuthController
 * @Description AuthController
 */
@Api("Users接口文档")
@RestController
@RequestMapping("/auth")
@Validated
public class AuthController {
    @Autowired
    private AuthService authService;

    @ApiOperation("授权")
    @PostMapping
    public Result authentication(@RequestParam("account") @NotNull String account, @RequestParam("password") @NotNull String password, Integer expire) {
        String token = authService.auth(account, password, expire);
        return Result.success(token);
    }
}
