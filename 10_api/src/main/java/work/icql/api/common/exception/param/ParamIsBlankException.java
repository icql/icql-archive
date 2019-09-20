package work.icql.api.common.exception.param;


import work.icql.api.common.exception.ServiceException;
import work.icql.api.common.result.ResultCode;

/**
 * @author icql
 * @version 1.0
 * @date 2018/12/3 16:08
 * @Title ParamIsBlankException
 * @Description ParamIsBlankException
 */
public class ParamIsBlankException extends ServiceException {

    @Override
    public ResultCode getResultCode() {
        return ResultCode.PARAM_IS_BLANK;
    }

    public ParamIsBlankException() {
    }

    public ParamIsBlankException(String message) {
        super(message);
    }

    public ParamIsBlankException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParamIsBlankException(Throwable cause) {
        super(cause);
    }

    public ParamIsBlankException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
