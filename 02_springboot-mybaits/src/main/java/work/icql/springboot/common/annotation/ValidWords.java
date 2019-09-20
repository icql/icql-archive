package work.icql.springboot.common.annotation;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.Arrays;
import java.util.List;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author icql
 * @version 1.0
 * @date 2018/11/22 17:12
 * @Title ValidWords
 * @Description ValidWords
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = ValidWords.Validator.class)
public @interface ValidWords {
    String message() default "ValidWords校验错误";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String[] words() default {};

    class Validator implements ConstraintValidator<ValidStatus, String> {
        private String[] values;

        @Override
        public void initialize(ValidStatus constraintAnnotation) {
            String[] strs = constraintAnnotation.strValue();
            this.values = strs;
        }

        @Override
        public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
            List<String> strs = Arrays.asList(values);
            if (strs.contains(s)) {
                return true;
            }
            return false;
        }
    }
}
