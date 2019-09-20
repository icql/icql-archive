package work.icql.springboot.common.exception.auth;

import work.icql.springboot.common.exception.ServiceException;
import work.icql.springboot.common.result.ResultCode;

/**
 * @author icql
 * @version 1.0
 * @date 2018/12/6 19:47
 * @Title AuthLoginErrorException
 * @Description AuthLoginErrorException
 */
public class AuthLoginErrorException extends ServiceException {
    @Override
    public ResultCode getResultCode() {
        return ResultCode.AUTH_LOGIN_ERROR;
    }

    public AuthLoginErrorException() {
    }

    public AuthLoginErrorException(String message) {
        super(message);
    }

    public AuthLoginErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthLoginErrorException(Throwable cause) {
        super(cause);
    }

    public AuthLoginErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
