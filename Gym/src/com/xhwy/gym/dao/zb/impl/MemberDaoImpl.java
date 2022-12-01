package com.xhwy.gym.dao.zb.impl;

import com.xhwy.gym.dao.zb.MemberDao;
import com.xhwy.gym.entity.MemberAll;
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
 * @date 2022/7/19 10:07
 */
public class MemberDaoImpl extends JdbcUtils implements MemberDao {
    @Override
    public List<Object> allMembers(String page, String limit) {
        //返回对象
        List<Object> members = new ArrayList<>();
        //连接
        Connection conn = getConnection();
        //sql
        String sql = " select member_account,member_name,member_gender,member_age,member_height,member_weight,member_phone,card_time,card_class,card_next_class  from member order by member_account desc limit ?,?";
        //page,limit转int
        int page1 = Integer.parseInt(page);
        int limit1 = Integer.parseInt(limit);
        //返回结果集对象
        ResultSet rs = exdcuteQuery(sql, (page1 - 1) * limit1, limit1);
        try {
            //解析
            while (rs.next()) {
                MemberAll member = new MemberAll();
                member.setMemberAccount(rs.getString(1));
                member.setMemberName(rs.getString(2));
                member.setMemberGender(rs.getString(3));
                member.setMemberAge(rs.getInt(4));
                member.setMemberHeight(rs.getInt(5));
                member.setMemberWeight(rs.getInt(6));
                member.setMemberPhone(rs.getLong(7));
                member.setCardTime(rs.getString(8));
                member.setCardClass(rs.getInt(9));
                member.setCardNextClass(rs.getInt(10));
                //放到list集合
                members.add(member);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //释放资源
            release(rs, null, conn);
        }
        //返回对象
        return members;
    }

    @Override
    public List<Object> allMembersLike(String page, String limit, String memberAccount, String memberName, String memberPhone, String cardTime) {
        //返回对象
        List<Object> members = new ArrayList<>();
        //连接
        Connection conn = getConnection();
        //sql
        String sql = "select  member_account,member_name,member_gender,member_age,member_height,member_weight,member_phone,card_time,card_class,card_next_class  from member " +
                " where member_account like '%" + memberAccount + "%' and member_name like '%" + memberName + "%' and member_phone like '%" + memberPhone + "%' and card_time like '%" + cardTime + "%'" +
                " order by card_time desc limit ?,?";
        //page,limit转int
        int page1 = Integer.parseInt(page);
        int limit1 = Integer.parseInt(limit);
        //返回结果集对象
        ResultSet rs = exdcuteQuery(sql, (page1 - 1) * limit1, limit1);
        try {
            //解析
            while (rs.next()) {
                MemberAll member = new MemberAll();
                member.setMemberAccount(rs.getString(1));
                member.setMemberName(rs.getString(2));
                member.setMemberGender(rs.getString(3));
                member.setMemberAge(rs.getInt(4));
                member.setMemberHeight(rs.getInt(5));
                member.setMemberWeight(rs.getInt(6));
                member.setMemberPhone(rs.getLong(7));
                member.setCardTime(rs.getString(8));
                member.setCardClass(rs.getInt(9));
                member.setCardNextClass(rs.getInt(10));
                //放到list集合
                members.add(member);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //释放资源
            release(rs, null, conn);
        }
        //返回对象
        return members;
    }

    @Override
    public int countMembers() {
        //返回对象
        int cnt = 0;
        //连接
        Connection conn = getConnection();
        //sql
        String sql = "select count(*) from member";
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
    public int editMember(MemberAll memberAll) {
        //返回对象
        int i = 0;
        //连接
        Connection conn = getConnection();
        //sql
        String sql = "update member set member_password=?,member_name=?,member_gender=?,member_age=?," +
                " member_height=?,member_weight=?,member_phone=?,card_class=?,card_next_class=? where" +
                " member_account=?";
        //预编译对象
        PreparedStatement stat = null;
        try {
            //解析
            stat = conn.prepareStatement(sql);
            stat.setObject(1, memberAll.getMemberPassword());
            stat.setObject(2, memberAll.getMemberName());
            stat.setObject(3, memberAll.getMemberGender());
            stat.setObject(4, memberAll.getMemberAge());
            stat.setObject(5, memberAll.getMemberHeight());
            stat.setObject(6, memberAll.getMemberWeight());
            stat.setObject(7, memberAll.getMemberPhone());
            stat.setObject(8, memberAll.getCardClass());
            stat.setObject(9, memberAll.getCardNextClass());
            stat.setObject(10, memberAll.getMemberAccount());
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
    public int addMember(MemberAll memberAll) {
        //返回对象
        int i = 0;
        //连接
        Connection conn = getConnection();
        //sql
        String sql = "insert into member(member_name,member_gender," +
                " member_age,member_height,member_weight,member_phone,card_class,card_next_class)" +
                " values(?,?,?,?,?,?,?,?)";
        //预编译对象
        PreparedStatement stat = null;
        try {
            //解析
            stat = conn.prepareStatement(sql);
            stat.setObject(1, memberAll.getMemberName());
            stat.setObject(2, memberAll.getMemberGender());
            stat.setObject(3, memberAll.getMemberAge());
            stat.setObject(4, memberAll.getMemberHeight());
            stat.setObject(5, memberAll.getMemberWeight());
            stat.setObject(6, memberAll.getMemberPhone());
            stat.setObject(7, memberAll.getCardClass());
            stat.setObject(8, memberAll.getCardNextClass());
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
    public int delMember(int memberAccount) {
        //返回对象
        int i = 0;
        //连接
        Connection conn = getConnection();
        //sql
        String sql = "delete from member where member_account = ?";
        //预编译对象
        PreparedStatement stat = null;
        try {
            //解析
            stat = conn.prepareStatement(sql);
            stat.setObject(1, memberAccount);
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
