package work.icql.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import work.icql.api.common.exception.auth.AuthIsErrorException;
import work.icql.api.common.exception.auth.AuthLoginErrorException;
import work.icql.api.common.util.CountLimiterUtils;
import work.icql.api.common.util.JwtUtils;
import work.icql.api.config.JwtConfig;
import work.icql.api.mapper.UserMapper;
import work.icql.api.pojo.entity.UserDO;
import work.icql.api.service.AuthService;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author icql
 * @version 1.0
 * @date 2018/12/5 14:00
 * @Title AuthServiceImpl
 * @Description AuthServiceImpl
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtConfig jwtConfig;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    @Qualifier("cacheTemplate")
    private Cache cacheTemplate;

    private final String loginLimitKeyPrefix = "api:auth:login:limit:";

    private final int loginErrorLimitCount = 5;

    private final long loginErrorDuration = 1;

    @Override
    public String auth(String nameOrEmail, String password, Integer expire) {

        Object result = CountLimiterUtils.caffeineLimit(cacheTemplate,
                loginLimitKeyPrefix + nameOrEmail,
                loginErrorLimitCount,
                loginErrorDuration,
                TimeUnit.MINUTES,
                "1分钟内已登录失败5次，请稍后再试",
                () -> {
                    UserDO user = userMapper.selectByNameOrEmail(nameOrEmail, nameOrEmail);
                    if (user == null) {
                        throw new AuthIsErrorException("此用户未注册");
                    }
                    if (user.getIsDeleted() == 1) {
                        throw new AuthIsErrorException("用户未激活或用户已禁用，请重新激活");
                    }
                    if (!encoder.matches(password, user.getPassword())) {
                        throw new AuthLoginErrorException("密码错误");
                    }

                    String token;
                    try {
                        token = generateToken(user, expire);
                    } catch (Exception e) {
                        throw new AuthIsErrorException("授权失败");
                    }
                    return token;
                });

        return result.toString();
    }

    /**
     * 计算token
     *
     * @param user
     * @return
     */
    private String generateToken(UserDO user, Integer expire) {
        Map<String, Object> claims = new HashMap<>(3);
        claims.put("id", user.getId());
        claims.put("name", user.getName());
        claims.put("email", user.getEmail());
        String token = JwtUtils.generateToken(claims, jwtConfig.getPrivateKey(), expire == null ? jwtConfig.getTokenExpire() : expire);

        return token;
    }
}
