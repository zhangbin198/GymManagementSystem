package com.xhwy.gym.util;

import java.sql.*;

public class Utils {
    public static String url = "jdbc:mysql://127.0.0.1:3306/gym?useSSL=false&useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&?zeroDateTimeBehavior=convertToNull";
    public static String user = "root";
    public static String password = "123456";

    //一般情况下，如果有些代码需要在项目启动的时候就执行，这时候就需要静态代码块。
    //比如一个项目启动需要加载的很多配置文件等资源，我们就可以都放入静态代码块中。
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        Connection con = null;

        try {
            con = DriverManager.getConnection(Utils.url, Utils.user, Utils.password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    public static Statement getStatement(Connection con) throws Exception {
        Statement statement = null;
        if (con != null) {
            statement = con.createStatement();
        }
        return statement;
    }

    public void close(ResultSet ret, Statement st, Connection con) {
        if (ret != null) {
            try {
                ret.close();
            } catch (SQLException e) {
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
        if (con != null) {

            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static ResultSet getResultSet(String sql) throws Exception {
        Connection con = Utils.getConnection();
        Statement ste = Utils.getStatement(con);
        ResultSet resultSet = ste.executeQuery(sql);
        return resultSet;
    }

    //影响行数
    public static int addLineOrdeleteLineOrModifyLine(String sql) throws Exception {
        Connection con = Utils.getConnection();
        Statement ste = Utils.getStatement(con);
        int update = ste.executeUpdate(sql);
        //return ste.executeUpdate(sql);
        return update;
    }
}


