package work.icql.springboot.common.exception.data;

import work.icql.springboot.common.exception.ServiceException;
import work.icql.springboot.common.result.ResultCode;

/**
 * @author icql
 * @version 1.0
 * @date 2019/4/3 16:09
 * @Title DataIsNoneException
 * @Description DataIsNoneException
 */
public class DataIsNoneException extends ServiceException {

    @Override
    public ResultCode getResultCode() {
        return ResultCode.DATA_IS_NONE;
    }

    public DataIsNoneException() {
    }

    public DataIsNoneException(String message) {
        super(message);
    }

    public DataIsNoneException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataIsNoneException(Throwable cause) {
        super(cause);
    }

    public DataIsNoneException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
