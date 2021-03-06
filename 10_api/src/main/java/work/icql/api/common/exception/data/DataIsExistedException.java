package work.icql.api.common.exception.data;


import work.icql.api.common.exception.ServiceException;
import work.icql.api.common.result.ResultCode;

/**
 * @author icql
 * @version 1.0
 * @date 2018/12/3 16:10
 * @Title DataIsExistedException
 * @Description DataIsExistedException
 */
public class DataIsExistedException extends ServiceException {

    @Override
    public ResultCode getResultCode() {
        return ResultCode.DATA_IS_EXISTED;
    }

    public DataIsExistedException() {
    }

    public DataIsExistedException(String message) {
        super(message);
    }

    public DataIsExistedException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataIsExistedException(Throwable cause) {
        super(cause);
    }

    public DataIsExistedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
