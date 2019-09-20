package com.icql.bookstore.servlet.order;

import com.icql.bookstore.service.OrderService;
import com.icql.bookstore.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/order/delete")
public class DeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("orderid");
        OrderService os = ServiceFactory.getOrderServiceInstance();
        try {
            os.deleteOrder(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("/order/list").forward(request, response);
    }
}
