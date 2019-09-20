package work.icql.ithome.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import work.icql.ithome.common.util.IdWorker;

/**
 * @author icql
 * @version 1.0
 * @date 2019/1/24 10:33
 * @Title SearchApplication
 * @Description SearchApplication
 */
@SpringBootApplication
public class SearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(SearchApplication.class, args);
    }

    @Bean
    public IdWorker idWorker() {
        return new IdWorker();
    }
}
