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

@WebServlet("/user/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        UserService us = ServiceFactory.getUserServiceInstance();
        try {
            String path = "/index.jsp";
            User user = us.login(username, password);

            if ("admin".equals(user.getRole())) {
                path = "/admin/login/home.jsp";
            }
            user.setPassword("");
            req.getSession().setAttribute("user", user);
            resp.sendRedirect(path);//req.getRequestDispatcher(path).forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("user_msg", e.getMessage());
            req.getRequestDispatcher("/user/login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
