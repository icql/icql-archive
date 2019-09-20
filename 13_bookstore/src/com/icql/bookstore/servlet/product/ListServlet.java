package com.icql.bookstore.servlet.product;

import com.icql.bookstore.bean.Page;
import com.icql.bookstore.bean.Product;
import com.icql.bookstore.service.ProductService;
import com.icql.bookstore.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/product/list")
public class ListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //导航按钮的查询条件
        String category = request.getParameter("category");
        String name = request.getParameter("name");
        Map<String, String> mapWhere = new HashMap<>();

        if (category != null && !"".equals(category)) {
            mapWhere.put("category", category);
        }
        if (name != null && !"".equals(name)) {
            mapWhere.put("name", name);
        }
        //初始化每页显示的记录数
        int pageSize = 4;
        int currentPage = 1;//当前页
        String currPage = request.getParameter("currentPage");//从上一页或下一页得到的数据
        if (currPage != null && !"".equals(currPage)) {//第一次访问资源时，currPage可能是null
            currentPage = Integer.parseInt(currPage);
        }

        ProductService ps = ServiceFactory.getProductServiceInstance();
        //分页查询，并返回PageBean对象
        Page<Product> page = null;
        try {
            page = ps.getPage4Product(currentPage, pageSize, mapWhere);
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("page", page);
        request.setAttribute("mapWhere", mapWhere);
        request.getRequestDispatcher("/product/list.jsp").forward(request, response);
    }
}
