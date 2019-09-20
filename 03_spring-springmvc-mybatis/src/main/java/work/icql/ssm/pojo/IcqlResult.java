package work.icql.ssm.pojo;

/**
 * @author icql
 * @version 1.0
 * @date 2018/10/31 11:09
 * @Title IcqlResult
 * @Description IcqlResult
 */
public class IcqlResult {

    // 响应业务状态
    private Integer status;
    // 响应消息
    private String msg;
    // 响应中的数据
    private Object data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


    public IcqlResult() {
    }

    public IcqlResult(Object data) {
        this(200, "OK", data);
    }

    public IcqlResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public static IcqlResult ok() {
        return new IcqlResult(null);
    }

    public static IcqlResult ok(Object data) {
        return new IcqlResult(data);
    }

    public static IcqlResult build(Integer status, String msg) {
        return new IcqlResult(status, msg, null);
    }

    public static IcqlResult build(Integer status, String msg, Object data) {
        return new IcqlResult(status, msg, data);
    }
}
