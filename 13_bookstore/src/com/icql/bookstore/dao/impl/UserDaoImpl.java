package com.icql.bookstore.dao.impl;

import com.icql.bookstore.bean.User;
import com.icql.bookstore.dao.UserDao;
import com.icql.bookstore.util.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    private QueryRunner qr = new QueryRunner();

    @Override
    public void Insert(User user) throws SQLException {
        String sql = "INSERT INTO USER(username,password,gender,email,telephone,introduce,activecode,state,registtime) VALUES(?,?,?,?,?,?,?,?,?)";

        qr.update(JdbcUtils.getConnection(), sql,
                user.getUsername(), user.getPassword(), user.getGender(), user.getEmail(), user.getTelephone(),
                user.getIntroduce(), user.getActiveCode(), user.getState(), user.getRegistTime());
    }

    @Override
    public void update(User user) throws SQLException {
        String sql = "UPDATE USER SET password=?,gender=?,email=?,telephone=?,introduce=? WHERE username=?";

        qr.update(JdbcUtils.getConnection(), sql,
                user.getPassword(), user.getGender(), user.getEmail(), user.getTelephone(),
                user.getIntroduce(), user.getUsername());
    }

    @Override
    public void delete(String username) throws SQLException {

    }

    @Override
    public User getUser(String username) throws SQLException {
        String sql = "select * from user where username=?";
        return qr.query(JdbcUtils.getConnection(), sql, new BeanHandler <User>(User.class), username);
    }

    @Override
    public User getUser(String username, String password) throws SQLException {
        String sql = "select * from user where username=? and password=?";
        return qr.query(JdbcUtils.getConnection(), sql, new BeanHandler <User>(User.class), username, password);
    }

    @Override
    public void active(String activeCode) throws SQLException {
        String sql = "UPDATE USER SET state=1 WHERE activecode=?";
        qr.update(JdbcUtils.getConnection(), sql, activeCode);
    }
}
