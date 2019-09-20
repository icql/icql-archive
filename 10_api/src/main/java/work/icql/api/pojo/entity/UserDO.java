package work.icql.api.pojo.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author icql
 * @version 1.0
 * @date 2018/12/23 14:10
 * @Title UserDO
 * @Description UserDO
 */
@Data
public class UserDO implements Serializable {

    private static final long serialVersionUID = 2433296796659007436L;

    private Long id;

    private String name;

    private String email;

    private String password;

    private Integer version;

    private Date gmtCreate;

    private Date gmtModified;

    private Byte isDeleted;
}