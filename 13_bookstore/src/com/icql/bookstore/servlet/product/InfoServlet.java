package com.icql.bookstore.servlet.product;

import com.icql.bookstore.bean.Product;
import com.icql.bookstore.service.ProductService;
import com.icql.bookstore.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/product/info")
public class InfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        ProductService ps =ServiceFactory.getProductServiceInstance();

        Product pd =new Product();
        try {
            pd = ps.getProduct(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("b",pd);
        request.getRequestDispatcher("/product/info.jsp").forward(request,response);
    }
}
