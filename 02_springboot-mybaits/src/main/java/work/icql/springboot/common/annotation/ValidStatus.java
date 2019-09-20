package work.icql.springboot.common.annotation;

import javax.validation.*;
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
 * @Title ValidStatus
 * @Description ValidStatus
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = ValidStatus.Validator.class)
public @interface ValidStatus {
    String message() default "ValidStatus校验错误";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int[] intValue() default {};

    String[] strValue() default {};


    class Validator implements ConstraintValidator<ValidStatus, Object> {
        private Integer[] intValidStatus;
        private String[] strValidStatus;

        @Override
        public void initialize(ValidStatus constraintAnnotation) {
            int[] ints = constraintAnnotation.intValue();
            String[] strs = constraintAnnotation.strValue();
            if (ints.length > 0 && strs.length > 0) {
                throw new ValidationException("ValidStatus注解的intValue和strValue只能选其一");
            }

            int n = ints.length;
            Integer[] integers = new Integer[n];
            for (int i : ints) {
                integers[i] = ints[i];
            }
            this.intValidStatus = integers;
            this.strValidStatus = strs;
        }

        @Override
        public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
            List<Integer> ints = Arrays.asList(intValidStatus);
            List<String> strs = Arrays.asList(strValidStatus);

            if (ints.contains(o) || strs.contains(o)) {
                return true;
            }
            return false;
        }
    }
}
