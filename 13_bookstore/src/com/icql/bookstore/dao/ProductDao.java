package com.icql.bookstore.dao;

import com.icql.bookstore.bean.Product;

import java.util.List;
import java.util.Map;

public interface ProductDao {
    /**
     * 获取Product总数
     *
     * @param mapWhere
     * @return
     * @throws Exception
     */
    int count(Map<String, String> mapWhere) throws Exception;

    /**
     * 分页获取List<Product>
     *
     * @param currentPage currentPage
     * @param pageSize    pageSize
     * @param mapWhere    Map<String,String>:Map<字段名,字段值> 使用非常有限，待改进
     * @return
     * @throws Exception
     */
    List<Product> getBooksByPage(int currentPage, int pageSize, Map<String, String> mapWhere) throws Exception;

    /**
     * 获取Product
     * @param id id
     * @return
     * @throws Exception
     */
    Product getProduct(String id) throws Exception;
}
