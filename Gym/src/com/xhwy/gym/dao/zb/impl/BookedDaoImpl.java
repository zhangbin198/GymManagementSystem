package com.xhwy.gym.dao.zb.impl;

import com.xhwy.gym.dao.zb.BookedDao;
import com.xhwy.gym.entity.Booked;
import com.xhwy.gym.util.JdbcUtils;
import com.xhwy.gym.util.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 张斌
 * @version 1.0
 * @date 2022/7/21 19:02
 */
public class BookedDaoImpl extends JdbcUtils implements BookedDao {
    @Override
    public List<Object> allBooked(String page, String limit) {
        //返回对象
        List<Object> bookeds = new ArrayList<>();
        //连接
        Connection conn = getConnection();
        //sql
        String sql = " select * from booked order by bill_id desc limit ?,?";
        //page,limit转int
        int page1 = Integer.parseInt(page);
        int limit1 = Integer.parseInt(limit);
        //结果集对象
        ResultSet rs = exdcuteQuery(sql, (page1 - 1) * limit1, limit1);
        try {
            //解析
            while (rs.next()) {
                Booked booked = new Booked();
                booked.setBillId(rs.getInt(1));
                booked.setBillingAccount(rs.getInt(2));
                booked.setBillName(rs.getString(3));
                booked.setWhetherMembers(rs.getString(4));
                booked.setBillMoney(rs.getInt(5));
                booked.setBillNotel(rs.getString(6));
                booked.setConsumerPlace(rs.getString(7));
                booked.setBillTime(rs.getString(8));
                //添加到集合
                bookeds.add(booked);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //释放资源
            release(rs, null, conn);
        }
        //返回对象
        return bookeds;
    }

    @Override
    public List<Object> allMembersLike(String page, String limit, String billId, String billingAccount, String billName, String billTime) {
        //返回对象
        List<Object> bookeds = new ArrayList<>();
        //连接
        Connection conn = getConnection();
        //sql
        String sql = " select * from booked " +
                " where bill_id like '%" + billId + "%' and Billing_account  like '%" + billingAccount + "%' and bill_name like '%" + billName + "%' and bill_time like '%" + billTime + "%' " +
                " order by bill_id desc limit ?,?";
        //page,limit转int
        int page1 = Integer.parseInt(page);
        int limit1 = Integer.parseInt(limit);
        //结果集对象
        ResultSet rs = exdcuteQuery(sql, (page1 - 1) * limit1, limit1);
        try {
            //解析
            while (rs.next()) {
                Booked booked = new Booked();
                booked.setBillId(rs.getInt(1));
                booked.setBillingAccount(rs.getInt(2));
                booked.setBillName(rs.getString(3));
                booked.setWhetherMembers(rs.getString(4));
                booked.setBillMoney(rs.getInt(5));
                booked.setBillNotel(rs.getString(6));
                booked.setConsumerPlace(rs.getString(7));
                booked.setBillTime(rs.getString(8));
                //放到list集合
                bookeds.add(booked);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //释放资源
            release(rs, null, conn);
        }
        //返回对象
        return bookeds;
    }

    @Override
    public int countBooked() {
        //返回对象
        int cnt = 0;
        //连接
        Connection conn = getConnection();
        //sql
        String sql = "select count(*) from booked";
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
    public int editBooked(Booked booked) {
        //返回对象
        int i = 0;
        //连接
        Connection conn = getConnection();
        String sql = "update booked set Billing_account=?,bill_name=?,Whether_members=?,bill_money=?,bill_notel=?,Consumer_Place=?" +
                " where bill_id=?";
        //预编译对象
        PreparedStatement stat = null;
        try {
            //解析
            stat = conn.prepareStatement(sql);
            stat.setObject(1, booked.getBillingAccount());
            stat.setObject(2, booked.getBillName());
            stat.setObject(3, booked.getWhetherMembers());
            stat.setObject(4, booked.getBillMoney());
            stat.setObject(5, booked.getBillNotel());
            stat.setObject(6, booked.getConsumerPlace());
            stat.setObject(7, booked.getBillId());
            //放到list集合
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
    public int addBooked(Booked booked) {
        //返回对象
        int i = 0;
        //连接
        Connection conn = getConnection();
        //sql
        String sql = "insert into booked(Billing_account,bill_name,Whether_members," +
                " bill_money,bill_notel,Consumer_Place)" +
                " values(?,?,?,?,?,?)";
        //预先编译对象
        PreparedStatement stat = null;
        try {
            //解析
            stat = conn.prepareStatement(sql);
            stat.setObject(1, booked.getBillingAccount());
            stat.setObject(2, booked.getBillName());
            stat.setObject(3, booked.getWhetherMembers());
            stat.setObject(4, booked.getBillMoney());
            stat.setObject(5, booked.getBillNotel());
            stat.setObject(6, booked.getConsumerPlace());
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
    public int delBooked(int booked) {
        //返回对象
        int i = 0;
        //连接
        Connection conn = getConnection();
        //sql
        String sql = "delete from booked where bill_id = ?";
        //预编译对象
        PreparedStatement stat = null;
        try {
            //解析
            stat = conn.prepareStatement(sql);
            stat.setObject(1, booked);
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
