package work.icql.ssm.service;

import org.springframework.ui.Model;
import work.icql.ssm.pojo.IcqlResult;
import work.icql.ssm.pojo.TbSSOUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author icql
 * @version 1.0
 * @date 2018/11/17 11:05
 * @Title SSOService
 * @Description SSOService
 */
public interface SSOService {

    /**
     * 登录
     *
     * @param request
     * @param model
     * @param service
     * @return
     */
    String login(HttpServletRequest request, Model model, String service);

    /**
     * 登录form表单
     *
     * @param request
     * @param response
     * @param service
     * @param username
     * @param password
     * @return
     */
    IcqlResult login4form(HttpServletRequest request, HttpServletResponse response, String service, String username, String password);

    /**
     * 验证ticket
     *
     * @param ticket
     * @return
     */
    IcqlResult validate(String ticket);

    /**
     * 登出
     *
     * @param request
     * @param response
     * @param service
     * @return
     */
    String logout(HttpServletRequest request, HttpServletResponse response, String service);

    /**
     * 注册验证
     *
     * @param type
     * @param value
     * @return
     */
    IcqlResult register4check(int type, String value);

    /**
     * 注册form表单
     *
     * @param user
     * @return
     */
    IcqlResult register4form(TbSSOUser user);
}
