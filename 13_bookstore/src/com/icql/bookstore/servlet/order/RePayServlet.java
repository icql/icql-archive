package com.icql.bookstore.servlet.order;

import com.icql.bookstore.bean.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/order/repay")
public class RePayServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String order_id = request.getParameter("orderid");
        String money = request.getParameter("money");
        Order order = new Order();
        order.setId(order_id);
         order.setMoney(Double.parseDouble(money));

        request.setAttribute("order",order);
        request.getRequestDispatcher("/order/pay.jsp").forward(request,response);
    }
}
