package com.icql.bookstore.dao;

import com.icql.bookstore.dao.impl.*;

/**
 * Dao工厂类
 */
public class DaoFactory {
    /**
     * 获取UserDao实例
     *
     * @return UserDao
     */
    public static UserDao getUserDaoInstance() {
        return new UserDaoImpl();
    }

    /**
     * 获取ProductDao实例
     *
     * @return
     */
    public static ProductDao getProductDaoInstance() {
        return new ProductDaoImpl();
    }

    /**
     * 获取CartDao实例
     *
     * @return
     */
    public static CartDao getCartDaoInstance() {
        return new CartDaoImpl();
    }

    /**
     * 获取OrderDao实例
     *
     * @return
     */
    public static OrderDao getOrderDaoInstance() {
        return new OrderDaoImpl();
    }

    /**
     * 获取OrderItemDao实例
     *
     * @return
     */
    public static OrderItemDao getOrderItemDaoInstance() {
        return new OrderItemDaoImpl();
    }
}
