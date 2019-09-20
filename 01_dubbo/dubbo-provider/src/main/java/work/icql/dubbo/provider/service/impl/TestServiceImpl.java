package work.icql.dubbo.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import work.icql.dubbo.api.TestService;

/**
 * @author icql
 * @version 1.0
 * @date 2018/12/25 9:36
 * @Title TestServiceImpl
 * @Description TestServiceImpl
 */
@Service(version = "1.0.0")
public class TestServiceImpl implements TestService {
    @Override
    public String test() {
        System.out.println("生产者方法");
        return "test";
    }
}
