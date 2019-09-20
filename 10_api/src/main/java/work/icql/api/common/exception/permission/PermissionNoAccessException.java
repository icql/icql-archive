package work.icql.api.common.exception.permission;


import work.icql.api.common.exception.ServiceException;
import work.icql.api.common.result.ResultCode;

/**
 * @author icql
 * @version 1.0
 * @date 2018/12/3 16:11
 * @Title PermissionNoAccessException
 * @Description PermissionNoAccessException
 */
public class PermissionNoAccessException extends ServiceException {
    @Override
    public ResultCode getResultCode() {
        return ResultCode.PERMISSION_NO_ACCESS;
    }

    public PermissionNoAccessException() {
    }

    public PermissionNoAccessException(String message) {
        super(message);
    }

    public PermissionNoAccessException(String message, Throwable cause) {
        super(message, cause);
    }

    public PermissionNoAccessException(Throwable cause) {
        super(cause);
    }

    public PermissionNoAccessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
