package work.icql.api.service;

/**
 * @author icql
 * @version 1.0
 * @date 2018/12/5 13:58
 * @Title AuthService
 * @Description AuthService
 */
public interface AuthService {
    /**
     *
     *
     * @param nameOrEmail
     * @param password
     * @param expire
     * @return
     */
    String auth(String nameOrEmail, String password, Integer expire);
}
