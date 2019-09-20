package work.icql.springboot.common.result;

import org.springframework.http.HttpStatus;

/**
 * @author icql
 * @version 1.0
 * @date 2018/11/29 11:15
 * @Title ResultCode
 * @Description API 统一返回状态码
 */
public enum ResultCode {
    /* 成功状态码 */
    SUCCESS(20000, "成功", HttpStatus.OK),
    SUCCESS_ADDITION1(20001, "成功", HttpStatus.OK),
    SUCCESS_ADDITION2(20002, "成功", HttpStatus.OK),
    SUCCESS_ADDITION3(20003, "成功", HttpStatus.OK),
    SUCCESS_ADDITION4(20004, "成功", HttpStatus.OK),


    /* 用户错误：10001-19999*/
    AUTH_IS_ERROR(10001, "认证失败，请登录", HttpStatus.UNAUTHORIZED),
    AUTH_NOT_LOGIN(10002, "认证失败，用户未登录", HttpStatus.UNAUTHORIZED),
    AUTH_LOGIN_ERROR(10003, "认证失败，用户不存在或密码错误", HttpStatus.UNAUTHORIZED),
    AUTH_IS_FORBIDDEN(10004, "认证失败，用户已被禁用", HttpStatus.UNAUTHORIZED),
    AUTH_IS_EXPIRED(10005, "认证过期，请刷新token", HttpStatus.UNAUTHORIZED),

    /* 参数错误：30001-39999 */
    PARAM_IS_INVALID(30001, "参数无效", HttpStatus.BAD_REQUEST),
    PARAM_IS_BLANK(30002, "参数为空", HttpStatus.BAD_REQUEST),
    PARAM_TYPE_ERROR(30003, "参数类型错误", HttpStatus.BAD_REQUEST),

    /* 数据错误：40001-49999 */
    DATA_IS_INVALID(40001, "数据无效", HttpStatus.BAD_REQUEST),
    DATA_IS_NONE(40002, "数据未找到", HttpStatus.BAD_REQUEST),
    DATA_IS_EXISTED(40004, "数据已存在", HttpStatus.BAD_REQUEST),
    DATA_IS_UPDATED(40005, "数据已被更新", HttpStatus.BAD_REQUEST),

    /* 系统错误：50001-59999 */
    SYSTEM_INNER_ERROR(50001, "系统繁忙，请稍后重试", HttpStatus.BAD_REQUEST),

    /* 接口错误：60001-69999 */
    INTERFACE_IS_ERROR(60001, "接口异常", HttpStatus.BAD_REQUEST),
    INTERFACE_IS_FORBIDDEN(60003, "接口禁止访问", HttpStatus.BAD_REQUEST),
    INTERFACE_IS_INVALID(60004, "接口地址无效", HttpStatus.BAD_REQUEST),
    INTERFACE_IS_TIMEOUT(60005, "接口请求超时", HttpStatus.BAD_REQUEST),
    INTERFACE_IS_HIGHLOAD(60006, "接口负载过高", HttpStatus.BAD_REQUEST),

    /* 权限错误：70001-79999 */
    PERMISSION_NO_ACCESS(70001, "无访问权限", HttpStatus.UNAUTHORIZED);

    private Integer code;
    private String message;
    private HttpStatus httpStatus;

    ResultCode(Integer code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public Integer code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }

    public HttpStatus httpStatus() {
        return this.httpStatus;
    }
}
