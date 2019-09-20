package work.icql.api.pojo.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author icql
 * @version 1.0
 * @date 2018/12/6 14:49
 * @Title VipDTO
 * @Description VipDTO
 */
@Data
public class VipDTO implements Serializable {

    private static final long serialVersionUID = -3940304441844865229L;

    @NotNull
    private Byte type;

    @NotNull
    private String content;
}
