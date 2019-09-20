package work.icql.springboot.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author icql
 * @version 1.0
 * @date 2019/3/30 14:37
 * @Title UserDTO
 * @Description UserDTO
 */
@Data
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 3598450396644918709L;

    private Long id;

    private String usercode;

    private String username;

    private String password;

    private Short age;

    private String memo;

    private String deleted;
}
