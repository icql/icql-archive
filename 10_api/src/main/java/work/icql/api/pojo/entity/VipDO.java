package work.icql.api.pojo.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author icql
 * @version 1.0
 * @date 2018/12/23 14:10
 * @Title VipDO
 * @Description VipDO
 */
@Data
public class VipDO implements Serializable {

    private static final long serialVersionUID = 2457221430010055253L;

    private Long id;

    private Byte type;

    private Date gmtCreate;

    private Date gmtModified;

    private String content;
}