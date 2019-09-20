package work.icql.springcloud.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import work.icql.springcloud.auth.config.JwtProperties;
import work.icql.springcloud.common.entity.JwtKey;
import work.icql.springcloud.common.util.JwtUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author icql
 * @version 1.0
 * @date 2018/10/24 16:46
 * @Title AuthService
 * @Description AuthService
 */
@Service
public class AuthService {
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * @param username
     * @param password
     * @return
     */
    public Map<String,Object> authentication(String username, String password) {
        //验证用户名密码是否正确

        String token = JwtUtils.generateToken("", "", jwtProperties.getPrivateKey(), jwtProperties.getExpire());
        Map<String,Object> map =new HashMap<>();
        map.put("token",token);
        map.put(JwtKey.JWT_KEY_ID,"");
        map.put(JwtKey.JWT_KEY_USERNAME,"");
        return map;
    }

    /**
     * @param token
     * @return
     */
    public String refresh(String token) {
        Map<String, Object> info = JwtUtils.getInfoByToken(jwtProperties.getPublicKey(), token);
        return JwtUtils.generateToken(info.get(JwtKey.JWT_KEY_ID).toString(), info.get(JwtKey.JWT_KEY_USERNAME).toString(), jwtProperties.getPrivateKey(), jwtProperties.getExpire());
    }
}
