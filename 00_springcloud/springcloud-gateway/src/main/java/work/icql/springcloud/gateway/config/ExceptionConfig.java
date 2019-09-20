package work.icql.springcloud.gateway.config;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import work.icql.service.common.entity.Result;
import work.icql.service.common.entity.StatusCode;

/**
 * @author icql
 * @version 1.0
 * @date 2018/10/24 15
 * @Title ExceptionConfig
 * @Description ExceptionConfig
 */
@RestControllerAdvice
public class ExceptionConfig {

    @ExceptionHandler(value = Exception.class)
    public Result error(Exception e) {
        e.printStackTrace();
        return Result.build(false, StatusCode.ERROR, e.getMessage());
    }
}
