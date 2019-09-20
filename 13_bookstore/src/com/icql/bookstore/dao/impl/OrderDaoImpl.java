package com.icql.bookstore.dao.impl;

import com.icql.bookstore.bean.Order;
import com.icql.bookstore.dao.OrderDao;
import com.icql.bookstore.util.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;

public class OrderDaoImpl implements OrderDao {
    @Override
    public void insert(Order order) throws Exception {
        String sql = "INSERT INTO `ORDER`(id,money,receiverAddress, receiverName,receiverPhone,username) VALUES(?,?,?,?,?,?)";
        QueryRunner qr = new QueryRunner();
        qr.update(JdbcUtils.getConnection(), sql, order.getId(), order.getMoney(), order.getReceiverAddress(),
                order.getReceiverName(), order.getReceiverPhone(), order.getUsername());
    }

    @Override
    public void updatePayState(String id) throws Exception {
        String sql = "update `ORDER` SET paystate = 1 where id = ?";
        QueryRunner qr = new QueryRunner();
        qr.update(JdbcUtils.getConnection(), sql, id);
    }

    @Override
    public List<Order> listOrder(String username) throws Exception {
        QueryRunner qr = new QueryRunner();
        String sql = "select * from `order` where username = ? ";
        return qr.query(JdbcUtils.getConnection(), sql, new BeanListHandler<Order>(Order.class), username);
    }

    @Override
    public Order getOrder(String id, String username) throws Exception {
        QueryRunner qr = new QueryRunner();
        String sql = "select * from `order` where username = ? and id =?";
        return qr.query(JdbcUtils.getConnection(), sql, new BeanHandler<Order>(Order.class), username, id);
    }

    @Override
    public void deleteOrder(String id) throws Exception {
        String sql = "delete from `ORDER` where id = ?";
        QueryRunner qr = new QueryRunner();
        qr.update(JdbcUtils.getConnection(), sql, id);
    }
}
