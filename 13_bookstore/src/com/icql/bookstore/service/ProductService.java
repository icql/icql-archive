package com.icql.bookstore.service;

import com.icql.bookstore.bean.Page;
import com.icql.bookstore.bean.Product;

import java.util.Map;

public interface ProductService {
    /**
     * 获取Page<Product>实例
     *
     * @param currentPage currentPage
     * @param pageSize    pageSize
     * @param mapWhere    mapWhere
     * @return
     * @throws Exception
     */
    Page<Product> getPage4Product(int currentPage, int pageSize, Map<String, String> mapWhere) throws Exception;

    /**
     * 根据id获取Product
     * @param id id
     * @return
     * @throws Exception
     */
    Product getProduct(String id) throws Exception;
}
