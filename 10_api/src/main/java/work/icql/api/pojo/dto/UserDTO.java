package work.icql.api.pojo.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import java.io.Serializable;

/**
 * @author icql
 * @version 1.0
 * @date 2018/12/4 15:54
 * @Title UserDTO
 * @Description UserDTO
 */
@Data
public class UserDTO implements Serializable {

    private static final long serialVersionUID = -7770813289925871843L;

    @Length(max = 50, message = "用户名：字符长度不能超过50")
    private String name;

    @Email(message = "用户邮箱：请输入合法的邮箱地址")
    @Length(max = 50, message = "用户邮箱：字符长度不能超过50")
    private String email;

    private String password;

    private String temp;
}
