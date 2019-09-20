package work.icql.springcloud.common.util;

import io.jsonwebtoken.*;
import org.joda.time.DateTime;
import work.icql.springcloud.common.entity.JwtKey;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

public final class JwtUtils {
    private JwtUtils() {
    }

    /**
     * 计算token
     * @param userId
     * @param username
     * @param privateKey
     * @param expireMinutes
     * @return
     */
    public static String generateToken(String userId, String username, PrivateKey privateKey, int expireMinutes) {
        return Jwts.builder()
                .claim(JwtKey.JWT_KEY_ID, userId)
                .claim(JwtKey.JWT_KEY_USERNAME, username)
                .setExpiration(DateTime.now().plusMinutes(expireMinutes).toDate())
                .signWith(SignatureAlgorithm.RS256, privateKey)
                .compact();
    }

    /**
     * 计算token
     * @param userId
     * @param username
     * @param privateKey
     * @param expireMinutes
     * @return
     * @throws Exception
     */
    public static String generateToken(String userId, String username, byte[] privateKey, int expireMinutes) throws Exception {
        return generateToken(userId, username, RsaUtils.getPrivateKey(privateKey), expireMinutes);
    }

    /**
     * 解析token
     * @param publicKey
     * @param token
     * @return
     */
    public static Jws<Claims> parseToken(PublicKey publicKey, String token) {
        return Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token);
    }

    /**
     * 解析token
     * @param publicKey
     * @param token
     * @return
     * @throws Exception
     */
    public static Jws<Claims> parseToken(byte[] publicKey, String token) throws Exception {
        return Jwts.parser().setSigningKey(RsaUtils.getPublicKey(publicKey)).parseClaimsJws(token);
    }

    /**
     * 获取token的信息
     * param publicKey
     * @param token
     * @return
     */
    public static Map<String, Object> getInfoByToken(PublicKey publicKey, String token) {
        Jws<Claims> claimsJws = parseToken(publicKey, token);
        Claims body = claimsJws.getBody();
        Map<String, Object> map = new HashMap<>();
        map.put(JwtKey.JWT_KEY_ID, body.get(JwtKey.JWT_KEY_ID));
        map.put(JwtKey.JWT_KEY_USERNAME, body.get(JwtKey.JWT_KEY_USERNAME));
        return map;
    }

    /**
     * 获取token的信息
     * @param publicKey
     * @param token
     * @return
     * @throws Exception
     */
    public static Map<String, Object> getInfoByToken(byte[] publicKey, String token) throws Exception {
        return getInfoByToken(RsaUtils.getPublicKey(publicKey), token);
    }
}
