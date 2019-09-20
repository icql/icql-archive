package com.icql.bookstore.servlet.user;

import com.icql.bookstore.bean.User;
import com.icql.bookstore.service.ServiceFactory;
import com.icql.bookstore.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/getinfo")
public class GetInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserService us = ServiceFactory.getUserServiceInstance();
        try {
            request.setAttribute("reqUser", us.getUserInfo(request.getParameter("username")));
            request.getRequestDispatcher("/user/updateinfo.jsp").forward(request,response);
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/user/login.jsp");
        }

    }
}
