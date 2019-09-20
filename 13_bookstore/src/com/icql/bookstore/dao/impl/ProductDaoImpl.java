package com.icql.bookstore.dao.impl;

import com.icql.bookstore.bean.Product;
import com.icql.bookstore.dao.ProductDao;
import com.icql.bookstore.util.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductDaoImpl implements ProductDao {
    @Override
    public int count(Map<String, String> mapWhere) throws Exception {
        QueryRunner qr = new QueryRunner();
        String sql = "select count(0) from product where 1=1 ";
        List lsVlaue = new ArrayList();
        if (mapWhere.size() > 0) {
            for (Map.Entry<String, String> item : mapWhere.entrySet()) {
                sql += " and " + item.getKey() + " like ? ";
                lsVlaue.add("%" + item.getValue() + "%");
            }
        }
        return (int) ((long) qr.query(JdbcUtils.getConnection(), sql, new ScalarHandler(1), lsVlaue.toArray()));
    }

    @Override
    public List<Product> getBooksByPage(int currentPage, int pageSize, Map<String, String> mapWhere) throws Exception {
        QueryRunner qr = new QueryRunner();
        String sql = "select * from product where 1=1 ";
        List lsVlaue = new ArrayList();

        if (mapWhere.size() > 0) {
            for (Map.Entry<String, String> item : mapWhere.entrySet()) {
                sql += " and " + item.getKey() + " like ? ";
                lsVlaue.add("%" + item.getValue() + "%");
            }
        }

        sql += " limit ?,?";

        lsVlaue.add((currentPage - 1) * pageSize);
        lsVlaue.add(pageSize);

        return qr.query(JdbcUtils.getConnection(), sql, new BeanListHandler<Product>(Product.class), lsVlaue.toArray());
    }

    @Override
    public Product getProduct(String id) throws Exception {
        QueryRunner qr = new QueryRunner();
        String sql = "select * from product where id = ?";
        return qr.query(JdbcUtils.getConnection(), sql, new BeanHandler<Product>(Product.class), id);
    }
}
