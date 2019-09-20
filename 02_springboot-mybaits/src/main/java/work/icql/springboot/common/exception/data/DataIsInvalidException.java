package work.icql.springboot.common.exception.data;

import work.icql.springboot.common.exception.ServiceException;
import work.icql.springboot.common.result.ResultCode;

/**
 * @author icql
 * @version 1.0
 * @date 2019/4/3 16:09
 * @Title DataIsInvalidException
 * @Description DataIsInvalidException
 */
public class DataIsInvalidException extends ServiceException {

    @Override
    public ResultCode getResultCode() {
        return ResultCode.DATA_IS_INVALID;
    }

    public DataIsInvalidException() {
    }

    public DataIsInvalidException(String message) {
        super(message);
    }

    public DataIsInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataIsInvalidException(Throwable cause) {
        super(cause);
    }

    public DataIsInvalidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
