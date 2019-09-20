package work.icql.springcloud.gateway.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import work.icql.springcloud.common.util.RsaUtils;

import javax.annotation.PostConstruct;
import java.security.PublicKey;

/**
 * @author icql
 * @version 1.0
 * @date 2018/10/23 15:21
 * @Title JwtProperties
 * @Description JwtProperties
 */
@ConfigurationProperties(prefix = "icql.jwt")
@Component
public class JwtProperties {

    @Autowired
    private RedisTemplate redisTemplate;

    private String redisKey4PublicKey;//redis公钥key

    private String requestHeaderKey;//requestHeaderKey

    private PublicKey publicKey; //公钥

    private static final Logger logger = LoggerFactory.getLogger(JwtProperties.class);

    @PostConstruct
    public void init() {
        try {
            String publicKeyStr = (String) redisTemplate.opsForValue().get(redisKey4PublicKey);
            if (StringUtils.isEmpty(publicKeyStr)) {
                throw new Exception();
            }
            this.publicKey = RsaUtils.getPublicKey(publicKeyStr.getBytes());
        } catch (Exception e) {
            logger.error("初始化公钥失败！", e);
            throw new RuntimeException();
        }
    }

    public String getRequestHeaderKey() {
        return requestHeaderKey;
    }

    public void setRequestHeaderKey(String requestHeaderKey) {
        this.requestHeaderKey = requestHeaderKey;
    }

    public String getRedisKey4PublicKey() {
        return redisKey4PublicKey;
    }

    public void setRedisKey4PublicKey(String redisKey4PublicKey) {
        this.redisKey4PublicKey = redisKey4PublicKey;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(PublicKey publicKey) {
        this.publicKey = publicKey;
    }
}
