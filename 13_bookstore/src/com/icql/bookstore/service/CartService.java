package com.icql.bookstore.service;

import com.icql.bookstore.bean.Cart;
import com.icql.bookstore.bean.Product;

import java.util.Map;

public interface CartService {
    /**
     * 增加product到购物车
     *
     * @param cart cart
     * @throws Exception
     */
    void add(Cart cart) throws Exception;

    /**
     * 根据用户名获取购物车内容
     *
     * @param username
     * @return
     * @throws Exception
     */
    Map <Cart, Product> mapCart(String username) throws Exception;

    /**
     * 更新购物车
     *
     * @param cart cart
     * @throws Exception
     */
    void update(Cart cart) throws Exception;
}
