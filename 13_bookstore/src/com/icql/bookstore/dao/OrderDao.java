package com.icql.bookstore.dao;

import com.icql.bookstore.bean.Order;

import java.util.List;

public interface OrderDao {
    /**
     * 新增
     *
     * @param order order
     * @throws Exception
     */
    void insert(Order order) throws Exception;

    /**
     * 修改支付状态
     *
     * @throws Exception
     */
    void updatePayState(String id) throws Exception;

    /**
     * 获取订单
     *
     * @param username username
     * @return
     * @throws Exception
     */
    List<Order> listOrder(String username) throws Exception;

    /**
     * 获取订单
     * @param id id
     * @param username username
     * @return
     * @throws Exception
     */
    Order getOrder(String id, String username) throws Exception;

    /**
     * 删除订单
     * @throws Exception
     */
    void deleteOrder(String id) throws Exception;
}
