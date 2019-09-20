package work.icql.springboot.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * @author icql
 * @version 1.0
 * @date 2018/11/22 13:36
 * @Title ValidatorConf
 * @Description ValidatorConf
 */
@Configuration
public class ValidatorConfig {

//    //校验单个参数
//    注意：一定要在Controller上加注解 @Validated，方法的参数直接使用上述注解匹配
//    @GetMapping("/test")
//    public void test(@Length(max = 5, message = "参数值长度最大为5") String test1, @Length(max = 4, message = "参数值长度最大为4") String test2) {}
//
//    //校验对象
//    @PostMapping("/test")
//    public String test(@Validated @RequestBody User user) {}

    @Bean
    public Validator validator(){
        ValidatorFactory validatorFactory = Validation.byProvider( HibernateValidator.class )
                .configure()
                //开启快速失败返回模式，验证失败即刻抛出异常
                //默认普通模式(会校验完所有的属性，然后返回所有的验证失败信息)
                .addProperty( "hibernate.validator.fail_fast", "true" )
                .buildValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        return validator;
    }
}
