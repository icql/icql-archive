package work.icql.ithome.qa.config;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import work.icql.ithome.common.entity.Result;
import work.icql.ithome.common.entity.StatusCode;

/**
 * @author icql
 * @version 1.0
 * @date 2019/1/24 15
 * @Title ExceptionConfig
 * @Description ExceptionConfig
 */
@RestControllerAdvice
public class ExceptionConfig {

    @ExceptionHandler(value = Exception.class)
    public Result error(Exception e) {
        e.printStackTrace();
        return new Result(false, StatusCode.ERROR, e.getMessage());
    }
}
