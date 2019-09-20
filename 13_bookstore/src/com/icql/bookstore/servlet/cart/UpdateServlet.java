package com.icql.bookstore.servlet.cart;

import com.icql.bookstore.bean.Cart;
import com.icql.bookstore.service.CartService;
import com.icql.bookstore.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cart/update")
public class UpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.valueOf(request.getParameter("id"));
        int num = Integer.valueOf(request.getParameter("num"));

        CartService cs = ServiceFactory.getCartServiceInstance();
        Cart cart = new Cart();
        cart.setCart_id(id);
        cart.setProduct_num(num);
        try {
            cs.update(cart);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("/cart/list");
    }
}
