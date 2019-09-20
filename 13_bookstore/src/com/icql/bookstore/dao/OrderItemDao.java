package com.icql.bookstore.dao;

import com.icql.bookstore.bean.OrderItem;

import java.util.List;

public interface OrderItemDao {
    /**
     * 新增
     * @param orderItem orderItem
     * @throws Exception
     */
    void insert(OrderItem orderItem) throws Exception;

    /**
     * 获取listOrderItem
     * @param orderId orderId
     * @return
     * @throws Exception
     */
    List<OrderItem> listOrderItem(String orderId) throws Exception;

    /**
     * 删除OrderItem
     * @param id
     * @throws Exception
     */
    void deleteOrderItem(String id) throws Exception;
}
