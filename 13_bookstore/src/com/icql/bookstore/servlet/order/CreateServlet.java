package com.icql.bookstore.servlet.order;

import com.icql.bookstore.bean.Order;
import com.icql.bookstore.bean.User;
import com.icql.bookstore.service.OrderService;
import com.icql.bookstore.service.ServiceFactory;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/order/create")
public class CreateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Order order = new Order();
        try {
            BeanUtils.populate(order, request.getParameterMap());
            order.setId(UUID.randomUUID().toString());
            order.setUsername(((User) request.getSession().getAttribute("user")).getUsername());//把session对象中的用户信息保存到order对象中
        } catch (Exception e) {
            e.printStackTrace();
        }

        OrderService os = ServiceFactory.getOrderServiceInstance();
        try {
            order = os.create(order);
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("order",order);
        request.getRequestDispatcher("/order/pay.jsp").forward(request, response);
    }
}
