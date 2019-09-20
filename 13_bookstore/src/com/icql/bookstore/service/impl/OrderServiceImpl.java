package com.icql.bookstore.service.impl;

import com.icql.bookstore.bean.Cart;
import com.icql.bookstore.bean.Order;
import com.icql.bookstore.bean.OrderItem;
import com.icql.bookstore.bean.Product;
import com.icql.bookstore.dao.*;
import com.icql.bookstore.service.OrderService;
import com.icql.bookstore.util.JdbcUtils;

import java.util.*;

public class OrderServiceImpl implements OrderService {
    private CartDao cartDao = DaoFactory.getCartDaoInstance();
    private OrderDao orderDao = DaoFactory.getOrderDaoInstance();
    private ProductDao productDao = DaoFactory.getProductDaoInstance();
    private OrderItemDao orderItemDao = DaoFactory.getOrderItemDaoInstance();

    @Override
    public Order create(Order order) throws Exception {
        List<Cart> lsCart = cartDao.listCart(order.getUsername());
        order.setId(UUID.randomUUID().toString());

        try {
            JdbcUtils.translationStart();
            orderDao.insert(order);
            for (Cart item : lsCart) {
                OrderItem oi = new OrderItem();
                oi.setOrder_id(order.getId());
                oi.setProduct_id(item.getProduct_id());
                oi.setBuynum(item.getProduct_num());

                orderItemDao.insert(oi);
            }

            cartDao.empty(order.getUsername());//清空购物车
            JdbcUtils.translationCommit();
        } catch (Exception e) {
            JdbcUtils.translationRollback();
        } finally {
            JdbcUtils.close();
        }
        return order;
    }

    @Override
    public boolean pay(String payType, String order_id, double money) throws Exception {
        boolean result = false;
        //封装数据 满足不同银行或第三方支付的数据接口

        //发送请求，调用接口，获取支付结果
        result = true;

        //修改订单支付状态
        if (result) {
            orderDao.updatePayState(order_id);
        }
        return result;
    }

    @Override
    public List<Order> listOrder(String username) throws Exception {
        return orderDao.listOrder(username);
    }

    @Override
    public Order getOrder(String orderid, String username) throws Exception {
        Order order = orderDao.getOrder(orderid, username);
        order.setLsOrderItem(orderItemDao.listOrderItem(orderid));
        for (OrderItem item : order.getLsOrderItem()) {
            item.setProduct(productDao.getProduct(item.getProduct_id()));
        }
        return order;
    }

    @Override
    public void deleteOrder(String id) throws Exception {
        try {
            JdbcUtils.translationStart();
            orderItemDao.deleteOrderItem(id);
            orderDao.deleteOrder(id);
            JdbcUtils.translationCommit();
        } catch (Exception e) {
            JdbcUtils.translationRollback();
        } finally {
            JdbcUtils.close();
        }

    }
}
