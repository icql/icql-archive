package work.icql.springboot.common.exception.auth;

import work.icql.springboot.common.exception.ServiceException;
import work.icql.springboot.common.result.ResultCode;

/**
 * @author icql
 * @version 1.0
 * @date 2018/12/6 19:46
 * @Title AuthNotLoginException
 * @Description AuthNotLoginException
 */
public class AuthNotLoginException extends ServiceException {
    @Override
    public ResultCode getResultCode() {
        return ResultCode.AUTH_NOT_LOGIN;
    }

    public AuthNotLoginException() {
    }

    public AuthNotLoginException(String message) {
        super(message);
    }

    public AuthNotLoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthNotLoginException(Throwable cause) {
        super(cause);
    }

    public AuthNotLoginException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
