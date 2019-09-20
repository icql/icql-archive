package work.icql.api.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.*;

/**
 * @author icql
 * @version 1.0
 * @date 2018/12/5 14:32
 * @Title JwtConfig
 * @Description JwtConfig
 */
@Data
@ConfigurationProperties(prefix = "jwt")
@Component
public class JwtConfig {
    /**
     * 密钥
     */
    private String secret;

    /**
     * token过期时间
     */
    private int tokenExpire;

    /**
     * 公钥
     */
    private PublicKey publicKey;

    /**
     * 私钥
     */
    private PrivateKey privateKey;

    @PostConstruct
    public void init() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        SecureRandom secureRandom = new SecureRandom(secret.getBytes());
        keyPairGenerator.initialize(1024, secureRandom);
        KeyPair keyPair = keyPairGenerator.genKeyPair();
        // 获取私钥公钥
        privateKey = keyPair.getPrivate();
        publicKey = keyPair.getPublic();
    }
}
