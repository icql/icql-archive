package com.icql.bookstore.dao;

import com.icql.bookstore.bean.Cart;

import java.util.List;

public interface CartDao {
    /**
     * 增加Product到购物车
     * @param cart cart
     * @throws Exception
     */
    void insert(Cart cart) throws Exception;

    /**
     * 更新
     * @param cart cart
     * @throws Exception
     */
    void update(Cart cart) throws Exception;

    /**
     * 删除
     * @param cart cart
     * @throws Exception
     */
    void delete(Cart cart) throws Exception;

    /**
     * 清空购物车
     * @param username
     * @throws Exception
     */
    void empty(String username) throws Exception;

    /**
     * 获取Cart
     * @param cart cart
     * @return
     * @throws Exception
     */
    Cart getCart(Cart cart) throws Exception;

    /**
     * 根据用户名获取购物车
     * @param username username
     * @return
     * @throws Exception
     */
    List<Cart> listCart(String username) throws Exception;
}
