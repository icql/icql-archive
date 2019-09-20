package com.icql.bookstore.servlet.user;

import com.icql.bookstore.bean.User;
import com.icql.bookstore.service.ServiceFactory;
import com.icql.bookstore.service.UserService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //处理验证码
        String validateCode = req.getParameter("validateCode");
        String sessionValidateCode = (String) req.getSession().getAttribute("validateCode");
        if (!sessionValidateCode.equals(validateCode)) {//如果两个验证码不一致，跳回注册面
            req.setAttribute("msgValidateCode", "验证码错误！");
            req.getRequestDispatcher("/user/register.jsp").forward(req, resp);
            return;
        }


        //获取表单数据
        User user = new User();
        try {
            BeanUtils.populate(user, req.getParameterMap());

            //表单数据验证。。。。待编码

            //调用业务逻辑
            UserService us = ServiceFactory.getUserServiceInstance();
            us.regist(user);

            //分发转向
            //要求用户激活后才能登录，所以不能把用户信息保存session中
            req.getRequestDispatcher("/user/register_success.jsp").forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("msgRegister", e.getMessage());
            req.getRequestDispatcher("/user/register.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
