package work.icql.api.common.exception.param;


import work.icql.api.common.exception.ServiceException;
import work.icql.api.common.result.ResultCode;

/**
 * @author icql
 * @version 1.0
 * @date 2018/12/3 16:08
 * @Title ParamTypeErrorException
 * @Description ParamTypeErrorException
 */
public class ParamTypeErrorException extends ServiceException {
    @Override
    public ResultCode getResultCode() {
        return ResultCode.PARAM_TYPE_ERROR;
    }

    public ParamTypeErrorException() {
    }

    public ParamTypeErrorException(String message) {
        super(message);
    }

    public ParamTypeErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParamTypeErrorException(Throwable cause) {
        super(cause);
    }

    public ParamTypeErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
