package com.xhwy.gym.util;

import java.sql.*;

/**
 * @author 张斌
 * @version 1.0
 * @date 2022/5/4 20:42
 */
public class JdbcUtils {
    private static String driver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/gym?useSSL=false&useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&?zeroDateTimeBehavior=convertToNull";
    private static String username = "root";
    private static String password = "123456";

    static {
        try {
            //1.驱动只用加载一次
            Class.forName(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取连接
     *
     * @return
     * @throws SQLException 异常抛出
     */
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 释放资源
     *
     * @param rs   结果集
     * @param st   执行
     * @param conn 连接
     */
    public static void release(ResultSet rs, Statement st, Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 增、删、改操作
     *
     * @param sql    sql语句
     * @param params 参数数组
     * @return 执行结果
     */
    protected int excuteUpdate(String sql, Object... params) {
        int result = 0;
        Connection conn = this.getConnection();
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                stat.setObject(i + 1, params[i]);
            }
            result = stat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            release(null, stat, conn);
        }
        return result;
    }

    /**
     * 查询操作
     *
     * @param sql    sql 语句
     * @param params 参数数组
     * @return 查询结果集
     */
    protected ResultSet exdcuteQuery(String sql, Object... params) {
        Connection conn = this.getConnection();
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            stat = conn.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                stat.setObject(i + 1, params[i]);
            }
            rs = stat.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
}
