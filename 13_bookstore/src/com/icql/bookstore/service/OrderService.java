package com.icql.bookstore.service;

import com.icql.bookstore.bean.Order;
import com.icql.bookstore.bean.OrderItem;

import java.util.List;
import java.util.Map;

public interface OrderService {
    /**
     * 创建订单
     *
     * @param order order
     * @throws Exception
     */
    Order create(Order order) throws Exception;

    /**
     * 支付
     *
     * @param payType  payType
     * @param order_id order_id
     * @param money    money
     * @return
     * @throws Exception
     */
    boolean pay(String payType, String order_id, double money) throws Exception;

    /**
     * 获取订单
     *
     * @param username username
     * @return
     */
    List<Order> listOrder(String username) throws Exception;

    /**
     * 获取订单
     *
     * @param orderid  orderid
     * @param username username
     * @return
     * @throws Exception
     */
    Order getOrder(String orderid, String username) throws Exception;

    /**
     * 删除订单
     * @param id
     * @throws Exception
     */
    void deleteOrder(String id) throws Exception;
}
