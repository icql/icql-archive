package work.icql.springboot.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import work.icql.springboot.common.result.Result;
import work.icql.springboot.common.util.CountLimiterUtils;
import work.icql.springboot.service.HelloService;

import java.util.concurrent.TimeUnit;

/**
 * @author icql
 * @version 1.0
 * @date 2019/3/27 13:21
 * @Title HelloController
 * @Description HelloController
 */
@Api("Hello test")
@RestController
public class HelloController {
    @Autowired
    private HelloService helloService;

    @Autowired
    private RedisTemplate redisTemplate;

    @ApiOperation("test缓存")
    @GetMapping("/test1")
    public void test1() {
        CountLimiterUtils.redisLimit(redisTemplate,
                "testtest",
                5,
                30,
                TimeUnit.SECONDS,
                "30秒内已错误5次",
                () -> {
                    throw new RuntimeException("错误");
                });
        //helloService.test();
    }


    @ApiOperation("test异常")
    @GetMapping("/test2")
    public Object test2() {
        Integer tests = helloService.tests();
        return Result.success(tests);
    }
}
