package work.icql.springboot.common.exception;

import work.icql.springboot.common.result.IResultCode;
import work.icql.springboot.common.result.ResultCode;

/**
 * @author icql
 * @version 1.0
 * @date 2019/3/30 11:39
 * @Title ServiceException
 * @Description ServiceException
 */
public class ServiceException extends RuntimeException implements IResultCode {
    @Override
    public ResultCode getResultCode() {
        return ResultCode.SYSTEM_INNER_ERROR;
    }

    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
