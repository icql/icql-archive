package work.icql.springboot.common.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author icql
 * @version 1.0
 * @date 2019/3/23 14:10
 * @Title MBStrFilter
 * @Description MBStrFilter
 */
@Target({FIELD})
@Retention(RUNTIME)
public @interface MBStrFilter {
    String[] value() default {};
}
