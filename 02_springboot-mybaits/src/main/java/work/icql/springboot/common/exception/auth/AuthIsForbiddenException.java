package work.icql.springboot.common.exception.auth;


import work.icql.springboot.common.exception.ServiceException;
import work.icql.springboot.common.result.ResultCode;

/**
 * @author icql
 * @version 1.0
 * @date 2018/12/6 19:47
 * @Title AuthIsForbiddenException
 * @Description AuthIsForbiddenException
 */
public class AuthIsForbiddenException extends ServiceException {
    @Override
    public ResultCode getResultCode() {
        return ResultCode.AUTH_IS_FORBIDDEN;
    }

    public AuthIsForbiddenException() {
    }

    public AuthIsForbiddenException(String message) {
        super(message);
    }

    public AuthIsForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthIsForbiddenException(Throwable cause) {
        super(cause);
    }

    public AuthIsForbiddenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
