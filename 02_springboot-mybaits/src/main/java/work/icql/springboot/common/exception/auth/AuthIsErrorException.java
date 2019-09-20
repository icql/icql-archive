package work.icql.springboot.common.exception.auth;


import work.icql.springboot.common.exception.ServiceException;
import work.icql.springboot.common.result.ResultCode;

/**
 * @author icql
 * @version 1.0
 * @date 2018/12/6 19:46
 * @Title AuthIsErrorException
 * @Description AuthIsErrorException
 */
public class AuthIsErrorException extends ServiceException {
    @Override
    public ResultCode getResultCode() {
        return ResultCode.AUTH_IS_ERROR;
    }

    public AuthIsErrorException() {
    }

    public AuthIsErrorException(String message) {
        super(message);
    }

    public AuthIsErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthIsErrorException(Throwable cause) {
        super(cause);
    }

    public AuthIsErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
