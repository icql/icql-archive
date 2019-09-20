package work.icql.springcloud.common.entity;

/**
 * @author icql
 * @version 1.0
 * @date 2019/1/24 10:14
 * @Title Result
 * @Description Result
 */
public class Result {
    private boolean flag;//是否成功
    private Integer code;// 返回码
    private String message;//返回信息
    private Object data;// 返回数据

    public Result() {
    }

    public Result(boolean flag, Integer code) {
        this(flag, code, null);
    }

    public Result(boolean flag, Integer code, String message) {
        this(flag, code, message, null);
    }

    public Result(boolean flag, Integer code, String message, Object data) {
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static Result ok() {
        return ok(null);
    }

    public static Result ok(Object data) {
        return new Result(true, StatusCode.OK, "请求成功", data);
    }

    public static Result ok(String message, Object data) {
        return new Result(true, StatusCode.OK, message, data);
    }

    public static Result build(boolean flag, Integer code) {
        return new Result(flag, code);
    }

    public static Result build(boolean flag, Integer code, String message) {
        return new Result(flag, code, message);
    }

    public static Result build(boolean flag, Integer code, String message, Object data) {
        return new Result(flag, code, message, data);
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
