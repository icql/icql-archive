package com.icql.bookstore.service;

import com.icql.bookstore.bean.User;

public interface UserService {

    /**
     * 注册用户
     *
     * @param user user
     * @throws Exception
     */
    void regist(User user) throws Exception;

    /**
     * 激活用户
     *
     * @param activeCode activeCode
     * @throws Exception
     */
    void active(String activeCode) throws Exception;

    /**
     * 登录
     *
     * @param username username
     * @param password password
     * @return
     * @throws Exception
     */
    User login(String username, String password) throws Exception;

    /**
     * 查找用户信息
     *
     * @param username username
     * @return
     * @throws Exception
     */
    User getUserInfo(String username) throws Exception;

    /**
     * 更新用户信息
     * @param user user
     * @throws Exception
     */
    void updateUserInfo(User user) throws Exception;
}
