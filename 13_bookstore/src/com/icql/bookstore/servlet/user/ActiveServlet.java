package com.icql.bookstore.servlet.user;

import com.icql.bookstore.service.ServiceFactory;
import com.icql.bookstore.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/active")
public class ActiveServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        UserService us = ServiceFactory.getUserServiceInstance();

        try {
            us.active(req.getParameter("activeCode"));
            resp.sendRedirect("/");
        } catch (Exception e) {
            e.printStackTrace();
            //用户提示失败信息
            resp.getWriter().write(e.getMessage());
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
