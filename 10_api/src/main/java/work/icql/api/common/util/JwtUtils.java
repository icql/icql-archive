package work.icql.api.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;
import java.util.Map;

/**
 * @author icql
 * @version 1.0
 * @date 2018/12/23 14:10
 * @Title JwtUtils
 * @Description JwtUtils
 */
public final class JwtUtils {
    private JwtUtils() {
    }

    /**
     * 计算token
     *
     * @param claims
     * @param privateKey
     * @param expire
     * @return
     */
    public static String generateToken(Map<String, Object> claims, PrivateKey privateKey, int expire) {
        JwtBuilder jwtBuilder = Jwts.builder().addClaims(claims).signWith(SignatureAlgorithm.RS256, privateKey);
        if (expire > 0) {
            jwtBuilder.setExpiration(new Date(System.currentTimeMillis() + expire * 60 * 1000));
        }
        return jwtBuilder.compact();
    }

    /**
     * 解析token
     *
     * @param publicKey
     * @param token
     * @return
     */
    public static Claims parseToken(PublicKey publicKey, String token) {
        return Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token).getBody();
    }
}
