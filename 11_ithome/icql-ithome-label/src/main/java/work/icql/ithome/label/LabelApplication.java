package work.icql.ithome.label;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import work.icql.ithome.common.util.IdWorker;

/**
 * @author icql
 * @version 1.0
 * @date 2019/1/24 10:33
 * @Title LabelApplication
 * @Description LabelApplication
 */
@SpringBootApplication
public class LabelApplication {
    public static void main(String[] args) {
        SpringApplication.run(LabelApplication.class, args);
    }


    @Bean
    public IdWorker idWorker() {
        return new IdWorker();
    }
}
