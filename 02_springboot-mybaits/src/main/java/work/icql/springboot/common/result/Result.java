package work.icql.springboot.common.result;

import lombok.Data;

import java.io.Serializable;

/**
 * @author icql
 * @version 1.0
 * @date 2018/11/29 11:13
 * @Title Result
 * @Description Result
 */
@Data
public class Result implements Serializable {
    private static final long serialVersionUID = -3948389268046368059L;

    private Integer code;

    private String msg;

    private Object data;

    private Result(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static Result success() {
        return new Result(ResultCode.SUCCESS.code(), ResultCode.SUCCESS.message(), null);
    }

    public static Result success(Object data) {
        return new Result(ResultCode.SUCCESS.code(), ResultCode.SUCCESS.message(), data);
    }

    public static Result failure(ResultCode resultCode) {
        return new Result(resultCode.code(), resultCode.message(), null);
    }

    public static Result failure(ResultCode resultCode, Object data) {
        return new Result(resultCode.code(), resultCode.message(), data);
    }
}
