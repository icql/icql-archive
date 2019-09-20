package work.icql.springboot.common.exception.auth;

import work.icql.springboot.common.exception.ServiceException;
import work.icql.springboot.common.result.ResultCode;

/**
 * @author icql
 * @version 1.0
 * @date 2018/12/6 19:51
 * @Title AuthIsExpiredException
 * @Description AuthIsExpiredException
 */
public class AuthIsExpiredException extends ServiceException {
    @Override
    public ResultCode getResultCode() {
        return ResultCode.AUTH_IS_EXPIRED;
    }

    public AuthIsExpiredException() {
    }

    public AuthIsExpiredException(String message) {
        super(message);
    }

    public AuthIsExpiredException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthIsExpiredException(Throwable cause) {
        super(cause);
    }

    public AuthIsExpiredException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
