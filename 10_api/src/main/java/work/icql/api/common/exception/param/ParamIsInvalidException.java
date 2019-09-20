package work.icql.api.common.exception.param;


import work.icql.api.common.exception.ServiceException;
import work.icql.api.common.result.ResultCode;

/**
 * @author icql
 * @version 1.0
 * @date 2019/3/29 15:33
 * @Title ParamIsInvalidException
 * @Description ParamIsInvalidException
 */
public class ParamIsInvalidException extends ServiceException {
    @Override
    public ResultCode getResultCode() {
        return ResultCode.PARAM_IS_INVALID;
    }

    public ParamIsInvalidException() {
    }

    public ParamIsInvalidException(String message) {
        super(message);
    }

    public ParamIsInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParamIsInvalidException(Throwable cause) {
        super(cause);
    }

    public ParamIsInvalidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}