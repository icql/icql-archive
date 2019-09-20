package work.icql.springboot.config;

import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.context.annotation.Profile;

/**
 * @author icql
 * @version 1.0
 * @date 2019/5/17 10:52
 * @Title JasyptConfig
 * @Description JasyptConfig
 */
@Profile("dev")
public class JasyptConfig {


    public static void main(String[] args) {
        //运行时请加入参数 盐 --jasypt.encryptor.password=salt

        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();

        //加密所需的salt(盐)
        textEncryptor.setPassword("xxx");

        //要加密的数据
        String password = textEncryptor.encrypt("xxx");

        //加密后的密文
        System.out.println("password:    " + "ENC(" + password + ")");
    }
}
