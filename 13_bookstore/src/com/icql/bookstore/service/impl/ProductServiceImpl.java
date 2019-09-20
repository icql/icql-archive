package com.icql.bookstore.service.impl;

import com.icql.bookstore.bean.Page;
import com.icql.bookstore.bean.Product;
import com.icql.bookstore.dao.DaoFactory;
import com.icql.bookstore.dao.ProductDao;
import com.icql.bookstore.service.ProductService;

import java.util.List;
import java.util.Map;

public class ProductServiceImpl implements ProductService {
    private ProductDao productDao = DaoFactory.getProductDaoInstance();

    @Override
    public Page<Product> getPage4Product(int currentPage, int pageSize, Map<String, String> mapWhere) throws Exception {
        try {
            int count = productDao.count(mapWhere);//得到总记录数
            int totalPage = count == 0 ? 1 : (int) Math.ceil(count * 1.0 / pageSize); //求出总页数
            List<Product> lsProduct = productDao.getBooksByPage(currentPage, pageSize, mapWhere);

            //把5个变量封装到PageBean中，做为返回值
            Page<Product> page = new Page<Product>();
            page.setCount(count);
            page.setTotalPage(totalPage);
            page.setCurrentPage(currentPage);
            page.setPageSize(pageSize);
            page.setLsT(lsProduct);

            return page;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Product getProduct(String id) throws Exception {
        return productDao.getProduct(id);
    }
}
