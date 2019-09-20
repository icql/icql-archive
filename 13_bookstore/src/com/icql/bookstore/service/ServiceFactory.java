package com.icql.bookstore.service;

import com.icql.bookstore.service.impl.CartServiceImpl;
import com.icql.bookstore.service.impl.OrderServiceImpl;
import com.icql.bookstore.service.impl.ProductServiceImpl;
import com.icql.bookstore.service.impl.UserServiceImpl;

public class ServiceFactory {
    /**
     * 获取UserService
     *
     * @return
     */
    public static UserService getUserServiceInstance() {
        return new UserServiceImpl();
    }

    /**
     * 获取ProductService
     *
     * @return
     */
    public static ProductService getProductServiceInstance() {
        return new ProductServiceImpl();
    }

    /**
     * 获取CartService
     *
     * @return
     */
    public static CartService getCartServiceInstance() {
        return new CartServiceImpl();
    }

    /**
     * 获取OrderService
     *
     * @return
     */
    public static OrderService getOrderServiceInstance() {
        return new OrderServiceImpl();
    }
}
