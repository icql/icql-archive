package work.icql.springcloud.auth.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import work.icql.springcloud.auth.service.AuthService;
import work.icql.springcloud.common.entity.Result;
import work.icql.springcloud.common.entity.StatusCode;

import java.util.Map;

/**
 * @author icql
 * @version 1.0
 * @date 2018/10/24 16:47
 * @Title AuthController
 * @Description AuthController
 */
@CrossOrigin
@RestController
public class AuthController {
    @Autowired
    private AuthService authService;

    /**
     * 登录授权
     *
     * @param username
     * @param password
     * @return
     */
    @PostMapping("authorization")
    public Result authentication(@RequestParam("username") String username, @RequestParam("password") String password) {
        Map<String, Object> authentication = authService.authentication(username, password);
        Result result;
        if (authentication == null) {
            result = Result.build(false, StatusCode.LOGINERROR);
        } else {
            result = Result.ok(authentication);
        }
        return result;
    }

    /**
     * 刷新授权
     *
     * @param token
     * @return
     */
    @PostMapping("refresh")
    public Result refresh(@RequestParam("token") String token) {
        String newToken = authService.refresh(token);
        Result result = null;
        if (StringUtils.isBlank(newToken)) {
            result = Result.build(false, StatusCode.ACCESSERROR);
        } else {
            result = Result.ok(newToken);
        }
        return result;
    }

}
