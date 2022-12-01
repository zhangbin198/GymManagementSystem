package com.xhwy.gym.dao.skc.Impl;

import com.xhwy.gym.dao.skc.ClassOrderDao;
import com.xhwy.gym.entity.ClassOrder;
import com.xhwy.gym.util.JdbcUtils;
import com.xhwy.gym.util.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 佘柯澂
 * @version 1.0
 * @date 2022/7/21 7:11
 */
public class ClassOrderDaoImpl extends JdbcUtils implements ClassOrderDao {
    @Override
    public List<Object> allEquipment(String page, String limit) {
        //返回对象
        List<Object> classOrders = new ArrayList<>();
        //连接
        Connection conn = getConnection();
        //sql
        String sql = " select * from class_order order by class_order_id desc limit ?,?";
        //page,limit转int
        int page1 = Integer.parseInt(page);
        int limit1 = Integer.parseInt(limit);
        //返回结果集
        ResultSet rs = exdcuteQuery(sql, (page1 - 1) * limit1, limit1);
        try {
            //解析
            while (rs.next()) {
                ClassOrder classOrder = new ClassOrder();
                classOrder.setClassOrderId(rs.getInt(1));
                classOrder.setClassName(rs.getString(2));
                classOrder.setClassPhone(rs.getString(3));
                classOrder.setClassTable(rs.getString(4));
                classOrder.setClassCoach(rs.getString(5));
                classOrder.setClassTime(rs.getString(6));
                classOrders.add(classOrder);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //释放资源
            release(rs, null, conn);
        }
        return classOrders;
    }

    @Override
    public List<Object> allClassOrderLike(String page, String limit, String classOrderId, String className, String classCoach, String classPhone) {
        //返回对象
        List<Object> classOrders = new ArrayList<>();
        //连接
        Connection conn = getConnection();
        //sql
        String sql = " select * from class_order " +
                " where class_order_id like '%" + classOrderId + "%' and class_name like '%" + className + "%' and class_coach like '%" + classCoach + "%' and class_Phone like '%" + classPhone + "%' " +
                " order by class_order_id desc limit ?,?";
        //page,limit转int
        int page1 = Integer.parseInt(page);
        int limit1 = Integer.parseInt(limit);
        //返回结果集对象
        ResultSet rs = exdcuteQuery(sql, (page1 - 1) * limit1, limit1);
        try {
            //解析
            while (rs.next()) {
                ClassOrder classOrder = new ClassOrder();
                classOrder.setClassOrderId(rs.getInt(1));
                classOrder.setClassName(rs.getString(2));
                classOrder.setClassPhone(rs.getString(3));
                classOrder.setClassTable(rs.getString(4));
                classOrder.setClassCoach(rs.getString(5));
                classOrder.setClassTime(rs.getString(6));
                classOrders.add(classOrder);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //释放资源
            release(rs, null, conn);
        }
        //返回对象
        return classOrders;
    }

    @Override
    public int countClassOrder() {
        //返回对象
        int cnt = 0;
        //连接对象
        Connection conn = getConnection();
        //sql
        String sql = "select count(*) from class_order";
        //结果集对象
        ResultSet rs = null;
        try {
            //解析
            rs = Utils.getResultSet(sql);
            if (rs.next()) {
                cnt = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            release(rs, null, conn);
        }
        //返回对象
        return cnt;
    }

    @Override
    public int editClassOrder(ClassOrder classOrder) {
        //返回对象
        int i = 0;
        //连接对象
        Connection conn = getConnection();
        //sql
        String sql = "update class_order set class_name=?,class_Phone=?,class_Table=?,class_coach=?,class_Time=? " +
                " where class_order_id=?";
        //预编译对象
        PreparedStatement stat = null;
        try {
            //解析
            stat = conn.prepareStatement(sql);
            stat.setObject(1, classOrder.getClassName());
            stat.setObject(2, classOrder.getClassPhone());
            stat.setObject(3, classOrder.getClassTable());
            stat.setObject(4, classOrder.getClassCoach());
            stat.setObject(5, classOrder.getClassTime());
            stat.setObject(6, classOrder.getClassOrderId());
            //返回受影响行数
            i = stat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //释放资源
            release(null, stat, conn);
        }
        //返回对象
        return i;
    }

    @Override
    public int addClassOrder(ClassOrder classOrder) {
        //返回对象
        int i = 0;
        //连接
        Connection conn = getConnection();
        //sql
        String sql = "insert into class_order(class_name,class_Phone,class_Table,class_coach,class_Time)values(?,?,?,?,?)";
        //预编译对象
        PreparedStatement stat = null;
        try {
            //解析
            stat = conn.prepareStatement(sql);
            stat.setObject(1, classOrder.getClassName());
            stat.setObject(2, classOrder.getClassPhone());
            stat.setObject(3, classOrder.getClassTable());
            stat.setObject(4, classOrder.getClassCoach());
            stat.setObject(5, classOrder.getClassTime());
            //返回受影响行数
            i = stat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //释放资源
            release(null, stat, conn);
        }
        return i;
    }

    @Override
    public int delClassOrder(int classOrder) {
        //返回对象
        int i = 0;
        //连接
        Connection conn = getConnection();
        //sql
        String sql = "delete from class_order where class_order_id = ?";
        //预编译对象
        PreparedStatement stat = null;
        try {
            //解析
            stat = conn.prepareStatement(sql);
            stat.setObject(1, classOrder);
            //返回受影响行数
            i = stat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //释放资源
            release(null, stat, conn);
        }
        //返回对象
        return i;
    }
}