package com.icql.bookstore.dao.impl;

import com.icql.bookstore.bean.Order;
import com.icql.bookstore.bean.OrderItem;
import com.icql.bookstore.dao.OrderItemDao;
import com.icql.bookstore.util.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;

public class OrderItemDaoImpl implements OrderItemDao {
    @Override
    public void insert(OrderItem orderItem) throws Exception {
        String sql="INSERT INTO orderitem(order_id,product_id,buynum) VALUES(?,?,?)";
        QueryRunner qr = new QueryRunner();
        qr.update(JdbcUtils.getConnection(),sql,orderItem.getOrder_id(),orderItem.getProduct_id(),orderItem.getBuynum());
    }

    @Override
    public List<OrderItem> listOrderItem(String orderId) throws Exception {
        QueryRunner qr = new QueryRunner();
        String sql = "select * from `orderitem` where order_id = ? ";
        return qr.query(JdbcUtils.getConnection(), sql, new BeanListHandler<OrderItem>(OrderItem.class), orderId);
    }

    @Override
    public void deleteOrderItem(String id) throws Exception {
        String sql="Delete from orderitem Where Order_id = ?";
        QueryRunner qr = new QueryRunner();
        qr.update(JdbcUtils.getConnection(),sql,id);
    }
}
