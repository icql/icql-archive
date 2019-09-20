package work.icql.springcloud.auth.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import work.icql.springcloud.common.util.RsaUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author icql
 * @version 1.0
 * @date 2018/10/23 15:21
 * @Title JwtProperties
 * @Description JwtProperties
 */
@Component
@ConfigurationProperties(prefix = "icql.jwt")
public class JwtProperties {

    @Autowired
    private RedisTemplate redisTemplate;

    private String secret; // 密钥

    private String redisKey4PrivateKey; //redis私钥key

    private String redisKey4PublicKey;//redis公钥key

    private int expire;// token过期时间

    private int refreshExpire;//刷新过期时间

    private PublicKey publicKey; // 公钥

    private PrivateKey privateKey; // 私钥

    private static final Logger logger = LoggerFactory.getLogger(JwtProperties.class);

    @PostConstruct
    public void init() {
        try {
            String privateKeyStr = (String) redisTemplate.opsForValue().get(redisKey4PrivateKey);
            String publicKeyStr = (String) redisTemplate.opsForValue().get(redisKey4PublicKey);

            if (StringUtils.isEmpty(publicKeyStr) || StringUtils.isEmpty(privateKeyStr)) {
                //计算私钥公钥并保存在redis中
                String[] keys = RsaUtils.generateKey(secret + UUID.randomUUID().toString()).split(",");
                privateKey = RsaUtils.getPrivateKey(keys[0].getBytes());
                publicKey = RsaUtils.getPublicKey(keys[1].getBytes());
                redisTemplate.opsForValue().set(redisKey4PrivateKey, keys[0]);
                redisTemplate.opsForValue().set(redisKey4PublicKey, keys[1]);

            } else {
                this.privateKey = RsaUtils.getPrivateKey(privateKeyStr.getBytes());
                this.publicKey = RsaUtils.getPublicKey(publicKeyStr.getBytes());
            }

        } catch (Exception e) {
            logger.error("初始化公钥和私钥失败！", e);
            throw new RuntimeException();
        }
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getRedisKey4PrivateKey() {
        return redisKey4PrivateKey;
    }

    public void setRedisKey4PrivateKey(String redisKey4PrivateKey) {
        this.redisKey4PrivateKey = redisKey4PrivateKey;
    }

    public String getRedisKey4PublicKey() {
        return redisKey4PublicKey;
    }

    public void setRedisKey4PublicKey(String redisKey4PublicKey) {
        this.redisKey4PublicKey = redisKey4PublicKey;
    }

    public int getExpire() {
        return expire;
    }

    public void setExpire(int expire) {
        this.expire = expire;
    }

    public int getRefreshExpire() {
        return refreshExpire;
    }

    public void setRefreshExpire(int refreshExpire) {
        this.refreshExpire = refreshExpire;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(PublicKey publicKey) {
        this.publicKey = publicKey;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(PrivateKey privateKey) {
        this.privateKey = privateKey;
    }

    public static Logger getLogger() {
        return logger;
    }
}
