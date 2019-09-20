package com.icql.bookstore.servlet.cart;

import com.icql.bookstore.bean.Cart;
import com.icql.bookstore.bean.User;
import com.icql.bookstore.service.CartService;
import com.icql.bookstore.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/cart/add")
public class AddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        Cart cart = new Cart();
        cart.setProduct_id(id);
        cart.setUser_name(((User)request.getSession().getAttribute("user")).getUsername());

        CartService cs =ServiceFactory.getCartServiceInstance();
        try {
            cs.add(cart);
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print("<a href='"+request.getContextPath()+"/product/list'>继续购物</a>，<a href='"+request.getContextPath()+"/cart/list'>查看购物车</a>");
    }
}
