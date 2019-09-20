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
import java.lang.reflect.InvocationTargetException;

@WebServlet("/user/updateinfo")
public class UpdateInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取表单数据
        UserService us = ServiceFactory.getUserServiceInstance();
        User user = new User();
        try {
            BeanUtils.populate(user,request.getParameterMap());
            us.updateUserInfo(user);
            request.getSession().invalidate();//相当于注消用户
            response.sendRedirect(request.getContextPath()+"/user/updateinfo_success.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write(e.getMessage());
        }
    }
}
