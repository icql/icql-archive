package com.icql.bookstore.dao.impl;

import com.icql.bookstore.bean.Cart;
import com.icql.bookstore.dao.CartDao;
import com.icql.bookstore.util.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;

public class CartDaoImpl implements CartDao {

    @Override
    public Cart getCart(Cart cart) throws Exception {
        QueryRunner qr = new QueryRunner();
        String sql = "select * from Cart where user_name = ? and product_id = ? ";
        return qr.query(JdbcUtils.getConnection(), sql, new BeanHandler <Cart>(Cart.class), cart.getUser_name(), cart.getProduct_id());
    }

    @Override
    public List <Cart> listCart(String username) throws Exception {
        QueryRunner qr = new QueryRunner();
        String sql = "select * from Cart where user_name = ?";
        return qr.query(JdbcUtils.getConnection(), sql, new BeanListHandler <Cart>(Cart.class), username);
    }

    @Override
    public void insert(Cart cart) throws Exception {
        QueryRunner qr = new QueryRunner();
        String sql = "INSERT INTO cart(product_id,product_num,user_name) VALUES(?,?,?)";
        qr.update(JdbcUtils.getConnection(), sql, cart.getProduct_id(), 1, cart.getUser_name());
    }

    @Override
    public void update(Cart cart) throws Exception {
        QueryRunner qr = new QueryRunner();
        String sql = "UPDATE cart SET product_num=? WHERE cart_id=?";
        qr.update(JdbcUtils.getConnection(), sql, cart.getProduct_num(), cart.getCart_id());
    }

    @Override
    public void delete(Cart cart) throws Exception {
        QueryRunner qr = new QueryRunner();
        String sql = "delete from cart WHERE cart_id=?";
        qr.update(JdbcUtils.getConnection(), sql, cart.getCart_id());
    }

    @Override
    public void empty(String username) throws Exception {
        QueryRunner qr = new QueryRunner();
        String sql = "delete from cart WHERE user_name=?";
        qr.update(JdbcUtils.getConnection(), sql, username);
    }
}
