package com.icql.bookstore.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JdbcUtils {
    private static ThreadLocal <Connection> tl = new ThreadLocal <Connection>();

    /**
     * 获取数据库连接Connection
     *
     * @return Connection
     */
    public static Connection getConnection() {
        Connection conn = tl.get();
        if (conn == null) {
            try {
                //c3p0 连接池，配置文件放在src根目录 dataSource = new ComboPooledDataSource();
                Context cxt = new InitialContext();
                conn = ((DataSource) cxt.lookup("java:/comp/env/jdbc/bookstore")).getConnection();
                tl.set(conn);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    /**
     * 开启事务
     */
    public static void translationStart() {
        try {
            getConnection().setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 提交事务
     */
    public static void translationCommit() {
        try {
            getConnection().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 回滚事务
     */
    public static void translationRollback() {
        try {
            getConnection().rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭数据库连接（Connection回池）
     */
    public static void close() {
        try {
            getConnection().close();
            tl.remove();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
