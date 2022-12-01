package com.xhwy.gym.dao.zb.impl;

import com.xhwy.gym.dao.zb.LeaveMessageDao;
import com.xhwy.gym.entity.Leavemessage;
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
 * @date 2022/7/21 21:40
 */
public class LeaveMessageDaoImpl extends JdbcUtils implements LeaveMessageDao {
    @Override
    public List<Object> allLeaveMessage(String page, String limit) {
        //返回对象
        List<Object> leaveMessages = new ArrayList<>();
        //连接
        Connection conn = getConnection();
        //sql
        String sql = " select * from leavemessage order by Leavemessage_id desc limit ?,?";
        //page,limit转int
        int page1 = Integer.parseInt(page);
        int limit1 = Integer.parseInt(limit);
        //返回结果集对象
        ResultSet rs = exdcuteQuery(sql, (page1 - 1) * limit1, limit1);
        try {
            //解析
            while (rs.next()) {
                Leavemessage leaveMessage = new Leavemessage();
                leaveMessage.setLeavemessageId(rs.getInt(1));
                leaveMessage.setLeavemessageUser(rs.getString(2));
                leaveMessage.setLeavemessageContent(rs.getString(3));
                leaveMessage.setLeavemessagePhone(rs.getString(4));
                leaveMessage.setLeavemessageTime(rs.getString(5));
                leaveMessages.add(leaveMessage);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //释放资源
            release(rs, null, conn);
        }
        //返回对象
        return leaveMessages;
    }

    @Override
    public List<Object> allMembersLike(String page, String limit, String leavemessageId, String leavemessageUser, String leavemessagePhone, String leavemessageTime) {
        //返回对象
        List<Object> leaveMessages = new ArrayList<>();
        //连接
        Connection conn = getConnection();
        //sql
        String sql = " select * from leavemessage " +
                " where Leavemessage_id like '%" + leavemessageId + "%' and Leavemessage__user  like '%" + leavemessageUser + "%' " +
                " and Leavemessage_phone like '%" + leavemessagePhone + "%' and leavemessage_time like '%" + leavemessageTime + "%'" +
                " order by Leavemessage_id desc limit ?,?";
        //page,limit转int
        int page1 = Integer.parseInt(page);
        int limit1 = Integer.parseInt(limit);
        //返回结果集对象
        ResultSet rs = exdcuteQuery(sql, (page1 - 1) * limit1, limit1);
        try {
            //解析
            while (rs.next()) {
                Leavemessage leaveMessage = new Leavemessage();
                leaveMessage.setLeavemessageId(rs.getInt(1));
                leaveMessage.setLeavemessageUser(rs.getString(2));
                leaveMessage.setLeavemessageContent(rs.getString(3));
                leaveMessage.setLeavemessagePhone(rs.getString(4));
                leaveMessage.setLeavemessageTime(rs.getString(5));
                //放到list集合
                leaveMessages.add(leaveMessage);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //释放资源
            release(rs, null, conn);
        }
        //返回对象
        return leaveMessages;
    }

    @Override
    public int countLeaveMessage() {
        //返回对象
        int cnt = 0;
        //连接
        Connection conn = getConnection();
        //sql
        String sql = "select count(*) from leavemessage";
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
            //释放资源
            release(rs, null, conn);
        }
        //返回对象
        return cnt;
    }

    @Override
    public int editLeaveMessage(Leavemessage leaveMessage) {
        //返回对象
        int i = 0;
        //连接
        Connection conn = getConnection();
        //sql
        String sql = "update leavemessage set Leavemessage__user=?,Leavemessage_content=?,Leavemessage_phone=?" +
                " where Leavemessage_id=?";
        //预编译对象
        PreparedStatement stat = null;
        try {
            //解析
            stat = conn.prepareStatement(sql);
            stat.setObject(1, leaveMessage.getLeavemessageUser());
            stat.setObject(2, leaveMessage.getLeavemessageContent());
            stat.setObject(3, leaveMessage.getLeavemessagePhone());
            stat.setObject(4, leaveMessage.getLeavemessageId());
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
    public int addLeaveMessage(Leavemessage leaveMessage) {
        //返回对象
        int i = 0;
        Connection conn = getConnection();
        //sql
        String sql = "insert into leavemessage(Leavemessage__user,Leavemessage_content," +
                " Leavemessage_phone)" +
                " values(?,?,?)";
        //预编译对象
        PreparedStatement stat = null;
        try {
            //解析
            stat = conn.prepareStatement(sql);
            stat.setObject(1, leaveMessage.getLeavemessageUser());
            stat.setObject(2, leaveMessage.getLeavemessageContent());
            stat.setObject(3, leaveMessage.getLeavemessagePhone());
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
    public int delLeaveMessage(int leaveMessage) {
        //返回对象
        int i = 0;
        //连接
        Connection conn = getConnection();
        //sql
        String sql = "delete from leavemessage where Leavemessage_id = ?";
        //预编译对象
        PreparedStatement stat = null;
        try {
            //解析
            stat = conn.prepareStatement(sql);
            stat.setObject(1, leaveMessage);
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
