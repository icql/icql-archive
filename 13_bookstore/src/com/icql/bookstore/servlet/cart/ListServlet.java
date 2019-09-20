package com.icql.bookstore.servlet.cart;

import com.icql.bookstore.bean.Cart;
import com.icql.bookstore.bean.Product;
import com.icql.bookstore.bean.User;
import com.icql.bookstore.service.CartService;
import com.icql.bookstore.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/cart/list")
public class ListServlet extends HttpServlet {
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
        request.getRequestDispatcher("/cart/list.jsp").forward(request, response);
    }
}
