package work.icql.ssm.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import work.icql.javatutil.FastjsonUtils;
import work.icql.javatutil.HttpClient;
import work.icql.ssm.pojo.IcqlResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author icql
 * @version 1.0
 * @date 2018/11/17 15:31
 * @Title SSOClientInterceptor
 * @Description SSOClientInterceptor
 */
@Component
public class SSOClientInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        HttpSession session = request.getSession();
        Object currentUser = session.getAttribute("currentUser");
        if (currentUser != null) {
            return true;
        }

        String requestUrl = request.getRequestURL().toString();
        String ticket = request.getParameter("ticket");
        if (ticket != null && ticket.length() > 0) {
            String validate = HttpClient.doPost("http://localhost:8080/sso/validate?ticket=" + ticket);
            IcqlResult resultValidate = FastjsonUtils.json2object(validate, IcqlResult.class);
            if (resultValidate.getStatus() == 200) {
                session.setAttribute("currentUser", resultValidate.getData());
                return true;
            }
            int index = requestUrl.indexOf("ticket");
            if (index != -1) {
                requestUrl = requestUrl.substring(0, index);
            }
        }

        response.sendRedirect("http://localhost:8080/sso/login?service=" + requestUrl);
        return false;
    }



    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {

    }
}
