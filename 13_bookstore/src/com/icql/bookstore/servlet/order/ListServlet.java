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
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/order/list")
public class ListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderService os = ServiceFactory.getOrderServiceInstance();
        User currentUser = (User) request.getSession().getAttribute("user");
        List<Order> lsOrder = new ArrayList<>();
        try {
            lsOrder = os.listOrder(currentUser.getUsername());
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("lsOrder", lsOrder);
        request.setAttribute("count", lsOrder.size());
        request.getRequestDispatcher("/order/list.jsp").forward(request,response);
    }
}
