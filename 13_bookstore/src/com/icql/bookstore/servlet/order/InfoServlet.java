package com.icql.bookstore.servlet.order;

import com.icql.bookstore.bean.Order;
import com.icql.bookstore.bean.User;
import com.icql.bookstore.service.OrderService;
import com.icql.bookstore.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/order/info")
public class InfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderid=request.getParameter("orderid");
        User currentUser = (User) request.getSession().getAttribute("user");
        OrderService os = ServiceFactory.getOrderServiceInstance();

        Order order = new Order();
        try {
            order = os.getOrder(orderid,currentUser.getUsername());
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("order",order);
        request.getRequestDispatcher("/order/info.jsp").forward(request,response);
    }
}
