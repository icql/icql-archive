package work.icql.ithome.spit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import work.icql.ithome.common.util.IdWorker;

/**
 * @author icql
 * @version 1.0
 * @date 2019/1/24 10:33
 * @Title SpitApplication
 * @Description SpitApplication
 */
@SpringBootApplication
public class SpitApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpitApplication.class, args);
    }


    @Bean
    public IdWorker idWorker() {
        return new IdWorker();
    }
}
