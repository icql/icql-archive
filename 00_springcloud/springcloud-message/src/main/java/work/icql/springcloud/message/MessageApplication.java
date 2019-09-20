package work.icql.springcloud.message;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author icql
 * @version 1.0
 * @date 2018/10/14 10:33
 * @Title MessageApplication
 * @Description MessageApplication
 */
@EnableDiscoveryClient
@SpringBootApplication
public class MessageApplication {
    public static void main(String[] args) {
        SpringApplication.run(MessageApplication.class, args);
    }
}
