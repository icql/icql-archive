package work.icql.dubbo.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import work.icql.dubbo.api.TestService;

/**
 * @author icql
 * @version 1.0
 * @date 2018/12/25 9:42
 * @Title TestController
 * @Description TestController
 */
@RestController
public class TestController {

    @Reference(version = "1.0.0")
    private TestService testService;

    @GetMapping("/test")
    public String test() {
        return testService.test();
    }
}
