package com.icql.bookstore.filter;

import com.icql.bookstore.bean.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter({"/include/*", "/order/*", "/cart/*", "/user/account", "/user/account.jsp", "/user/updateinfo.jsp", "/user/updateinfo_success.jsp", "/user/updateinfo"})
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        User user = (User) request.getSession().getAttribute("user");
        //判断user是否为null
        if (user != null) {
            chain.doFilter(req, resp);
        } else {
            response.sendRedirect(request.getContextPath() + "/user/login.jsp");
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
