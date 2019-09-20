package work.icql.springboot.common.exception.data;

import work.icql.springboot.common.exception.ServiceException;
import work.icql.springboot.common.result.ResultCode;

/**
 * @author icql
 * @version 1.0
 * @date 2019/3/31 16:58
 * @Title DataIsUpdatedException
 * @Description DataIsUpdatedException
 */
public class DataIsUpdatedException extends ServiceException {
    @Override
    public ResultCode getResultCode() {
        return ResultCode.DATA_IS_UPDATED;
    }

    public DataIsUpdatedException() {
    }

    public DataIsUpdatedException(String message) {
        super(message);
    }

    public DataIsUpdatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataIsUpdatedException(Throwable cause) {
        super(cause);
    }

    public DataIsUpdatedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
