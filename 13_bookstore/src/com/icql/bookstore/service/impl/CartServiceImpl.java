package com.icql.bookstore.service.impl;

import com.icql.bookstore.bean.Cart;
import com.icql.bookstore.bean.Product;
import com.icql.bookstore.dao.CartDao;
import com.icql.bookstore.dao.DaoFactory;
import com.icql.bookstore.dao.ProductDao;
import com.icql.bookstore.service.CartService;

import java.util.HashMap;
import java.util.Map;

public class CartServiceImpl implements CartService {
    private CartDao cartDao = DaoFactory.getCartDaoInstance();
    private ProductDao productDao = DaoFactory.getProductDaoInstance();

    @Override
    public void add(Cart cart) throws Exception {
        Cart oldCart = cartDao.getCart(cart);
        if (oldCart == null) {
            cartDao.insert(cart);
        } else {
            oldCart.setProduct_num(oldCart.getProduct_num() + 1);
            cartDao.update(oldCart);
        }
    }

    @Override
    public Map <Cart, Product> mapCart(String username) throws Exception {
        Map <Cart, Product> map = new HashMap <>();
        for(Cart item : cartDao.listCart(username)){
            map.put(item,productDao.getProduct(item.getProduct_id()));
        }
        return map;
    }

    @Override
    public void update(Cart cart) throws Exception {
        if(cart.getProduct_num() == 0){
            cartDao.delete(cart);
        }else{
            cartDao.update(cart);
        }
    }
}
