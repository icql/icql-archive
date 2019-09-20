package work.icql.javatutil;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 问题较多，应该建立线程池，每次查询完都关闭了连接，ThreadLocal没起到作用
 */
public final class JdbcUtils {

    private JdbcUtils() {
    }

    private static ThreadLocal<Connection> tl = new ThreadLocal<>();
    private static Connection conn;

    public static void openConnection(JdbcSetting jdbcSetting) {
        conn = tl.get();
        if (conn == null) {
            try {
                switch (jdbcSetting.getDbType()) {
                    case MYSQL:
                        Class.forName("com.mysql.jdbc.Driver");
                        break;
                    case SQLSERVER:
                        Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
                        break;
                    case ORACLE:
                        Class.forName("oracle.jdbc.driver.OracleDriver");
                        break;
                }
                conn = DriverManager.getConnection(jdbcSetting.getDbUrl(), jdbcSetting.getDbUser(), jdbcSetting.getPassword());
                tl.set(conn);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeConnection() {
        try {
            conn.close();
            tl.remove();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void openTranslation() {
        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void commitTranslation() {
        try {
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void rollbackTranslation() {
        try {
            conn.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * 执行query
     *
     * @param jdbcSetting
     * @param clazz
     * @param sql
     * @param params
     * @param <T>         字段名与 sql查询出的字段名 大小写名称必须一致
     * @return
     */
    public static <T> List<T> executeQuery(JdbcSetting jdbcSetting, Class<T> clazz, String sql, List<Object> params) {
        openConnection(jdbcSetting);
        List<T> result = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = conn.prepareStatement(sql);
            if (params != null && !params.isEmpty()) {
                for (int i = 0; i < params.size(); i++) {
                    preparedStatement.setObject(i + 1, params.get(i));
                }
            }
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                T t = clazz.newInstance();
                Field[] declaredFields = clazz.getDeclaredFields();
                for (Field field : declaredFields) {
                    Object columnValue = resultSet.getObject(field.getName());
                    field.setAccessible(true);
                    field.set(t, columnValue);
                }
                result.add(t);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        closeConnection();
        return result;

    }

    /**
     * 执行update/insert/delete
     *
     * @param jdbcSetting
     * @param sql
     * @param params
     * @return
     */
    public static int executeUpdate(JdbcSetting jdbcSetting, String sql, List<Object> params) {
        openConnection(jdbcSetting);
        int result = -1;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(sql);
            if (params != null && !params.isEmpty()) {
                for (int i = 0; i < params.size(); i++) {
                    preparedStatement.setObject(i + 1, params.get(i));
                }
            }
            result = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        closeConnection();
        return result;
    }

    /**
     * 获取JdbcSetting
     *
     * @param dbType
     * @param dbUrl
     * @param dbUser
     * @param password
     * @return
     */
    public static JdbcSetting getJdbcSetting(JdbcDbtype dbType, String dbUrl, String dbUser, String password) {
        return new JdbcSetting(dbType, dbUrl, dbUser, password);
    }
}