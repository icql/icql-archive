package com.icql.bookstore.servlet.order;

import com.icql.bookstore.service.OrderService;
import com.icql.bookstore.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/order/pay")
public class PayServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String order_id = request.getParameter("order_id");
        String money = request.getParameter("money");
        String payType = request.getParameter("yh");

        OrderService os = ServiceFactory.getOrderServiceInstance();
        boolean result = false;
        try {
            result = os.pay(payType, order_id, Double.valueOf(money));
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(result){
            response.sendRedirect("/order/pay_success.jsp");
        }else{
            request.setAttribute("msg","支付失败，请重新支付");
            request.getRequestDispatcher(".order/pay").forward(request,response);
        }

    }
}
