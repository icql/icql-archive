package work.icql.springcloud.common.entity;

/**
 * @author icql
 * @version 1.0
 * @date 2019/1/24 10:18
 * @Title StatusCode
 * @Description StatusCode
 */
public class StatusCode {
    public static final int OK = 20000;//成功
    public static final int ERROR = 20001;//失败
    public static final int LOGINERROR = 20002;//用户名或密码错误
    public static final int EXPIREDERROR = 20003;//授权过期
    public static final int ACCESSERROR = 20004;//授权失败
    public static final int REMOTEERROR = 20005;//远程调用失败
    public static final int REPERROR = 20005;//重复操作
}
