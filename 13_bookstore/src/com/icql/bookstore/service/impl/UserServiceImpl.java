package com.icql.bookstore.service.impl;

import com.icql.bookstore.bean.User;
import com.icql.bookstore.dao.DaoFactory;
import com.icql.bookstore.dao.UserDao;
import com.icql.bookstore.service.UserService;
import com.icql.bookstore.util.MailUtils;
import org.apache.commons.codec.digest.DigestUtils;

public class UserServiceImpl implements UserService {
    private UserDao userdao = DaoFactory.getUserDaoInstance();

    @Override
    public void regist(User user) throws Exception {
        if (userdao.getUser(user.getUsername()) == null) {
            user.setActiveCode(DigestUtils.md5Hex(user.getUsername()) + user.getUsername());//md5加密明文 设置激活码
            user.setPassword(DigestUtils.sha256Hex(user.getPassword() + "icql"));//sha256加密明文 设置密码

            try {
                userdao.Insert(user);
            } catch (Exception e) {
                throw new Exception("注册失败");
            }
            String emailSubject = "用户激活";
            String emailContent = "注册成功，请<a href='http://localhost:8081/user/active?activeCode=" + user.getActiveCode() + "'>激活</a>后登录";
            MailUtils.sendMail(user.getEmail(), emailSubject, emailContent);
        } else {
            throw new Exception("用户名已存在，请重新输入");
        }
    }

    @Override
    public void active(String activeCode) throws Exception {
        try {
            userdao.active(activeCode);
        } catch (Exception e) {
            throw new Exception("激活失败");
        }
    }

    @Override
    public User login(String username, String password) throws Exception {
        User user = userdao.getUser(username, DigestUtils.sha256Hex(password + "icql"));
        if (user == null) {
            throw new Exception("用户名不存在或密码错误");
        } else if (user.getState() == 0) {
            throw new Exception("该用户未激活，请查看邮箱激活此账户");
        } else {
            return user;
        }
    }

    @Override
    public User getUserInfo(String username) throws Exception {
        try {
            User user = userdao.getUser(username);
            if (user == null) {
                throw new Exception("未查找到用户信息");
            } else {
                return user;
            }
        } catch (Exception e) {
            throw new Exception("未查找到用户信息");
        }
    }

    @Override
    public void updateUserInfo(User user) throws Exception {
        try {
            user.setPassword(DigestUtils.sha256Hex(user.getPassword() + "icql"));//sha256加密明文 设置密码
            userdao.update(user);
        } catch (Exception e) {
            throw new Exception("用户信息更新失败");
        }
    }
}