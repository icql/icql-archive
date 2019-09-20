package com.icql.bookstore.servlet.order;

import com.icql.bookstore.bean.User;
import com.icql.bookstore.service.CartService;
import com.icql.bookstore.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/order/settlement")
public class SettlementServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CartService cs = ServiceFactory.getCartServiceInstance();
        User currentUser = (User) request.getSession().getAttribute("user");
        try {
            request.setAttribute("mapCart", cs.mapCart((currentUser.getUsername())));
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("/order/settlement.jsp").forward(request, response);
    }
}
