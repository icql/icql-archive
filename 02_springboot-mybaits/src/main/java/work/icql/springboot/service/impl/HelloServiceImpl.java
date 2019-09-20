package work.icql.springboot.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import work.icql.springboot.common.annotation.FailedRetry;
import work.icql.springboot.common.exception.data.DataIsUpdatedException;
import work.icql.springboot.entity.User;
import work.icql.springboot.entity.UserExample;
import work.icql.springboot.mapper.UserMapper;
import work.icql.springboot.service.HelloService;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author icql
 * @version 1.0
 * @date 2019/3/27 13:24
 * @Title HelloServiceImpl
 * @Description HelloServiceImpl
 */
@Service
@Slf4j
public class HelloServiceImpl implements HelloService {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserMapper userMapper;

    @SuppressWarnings("unchecked")
    @Override
    public void test() {

        //Long add = redisTemplate.opsForSet().add("VIN", "12346481");
        //Set members = redisTemplate.opsForSet().members("VIN");
        //String[] arr = new String[]{"-9999", "A", "2", "B", "3", "C", "4", "D", "9999"};
        //double val = 2.5;
        //String a = "";
        //for (int i = 2; i < arr.length; i = i + 2) {
        //    double small = Double.valueOf(arr[i - 2]);
        //    double big = Double.valueOf(arr[i]);
        //
        //    if (val >= small && val < big) {
        //        a = arr[i - 1];
        //        break;
        //    }
        //}
        //log.error(a);
        //
        //log.error("缓存了");
    }

    @FailedRetry(maxRetries = 5, durationSeconds = 2, exceptionClazz = {DataIsUpdatedException.class})
    public Integer tests() {
        User user = new User();
        user.setId(5l);
        user.setAge((short) 12);
        user.setPassword("新密码");

        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(5l);
        criteria.andAgeEqualTo((short) 12);
        int count = userMapper.updateByExampleSelective(user, example);
        if (count == 0) {
            throw new DataIsUpdatedException("数据已被其他人更新");
        }
        return count;
    }

    //@Scheduled(cron = "*/5 * * * * ?")
    public void testTask() {
        System.out.println("定时任务");
    }

    @Test
    public void testAA() {
        try {
            int i = 1 / 0;
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            System.out.println(sw.toString());
        }
    }
}
