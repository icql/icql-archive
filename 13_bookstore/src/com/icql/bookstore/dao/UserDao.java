package com.icql.bookstore.dao;

import com.icql.bookstore.bean.User;

import java.sql.SQLException;

public interface UserDao {
    /**
     * 新增用户
     *
     * @param user user
     * @throws SQLException
     */
    void Insert(User user) throws SQLException;

    /**
     * 更新用户
     *
     * @param user user
     * @throws SQLException
     */
    void update(User user) throws SQLException;

    /**
     * 删除用户
     *
     * @param username username
     * @throws SQLException
     */
    void delete(String username) throws SQLException;

    /**
     * 获取用户
     *
     * @param username username
     * @return user
     */
    User getUser(String username) throws SQLException;

    /**
     * 获取用户
     *
     * @param username username
     * @param password password
     * @return user
     */
    User getUser(String username, String password) throws SQLException;

    /**
     * 激活用户
     *
     * @param activeCode activeCode
     * @throws SQLException
     */
    void active(String activeCode) throws SQLException;
}
