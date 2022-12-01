package com.xhwy.gym.dao.zb.impl;

import com.xhwy.gym.dao.zb.LoginDao;
import com.xhwy.gym.entity.Admin;
import com.xhwy.gym.entity.Coach;
import com.xhwy.gym.entity.MemberAll;
import com.xhwy.gym.util.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 管理员登录数据实现层
 *
 * @author 张斌
 * @version 1.0
 * @date 2022/7/18 6:42
 */
public class LoginDaoImpl extends JdbcUtils implements LoginDao {
    @Override
    public Admin login(String username, String password) {
        //返回对象
        Admin admin = null;
        //结果集对象
        ResultSet rs = null;
        //预编译对象
        PreparedStatement stat = null;
        //连接
        Connection conn = this.getConnection();
        //sql
        String sql = "select * from admin where admin_username = ? and admin_password = ?";
        try {
            //设置参数
            stat = conn.prepareStatement(sql);
            stat.setObject(1, username);
            stat.setString(2, password);
            rs = stat.executeQuery();
            //解析
            while (rs.next()) {
                admin = new Admin();
                admin.setAdminAccount(rs.getInt(2));
                admin.setAdminPassword(rs.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            release(rs, stat, conn);
        }
        return admin;
    }

    @Override
    public int addLogin(Admin admin) {
        //返回对象
        int i = 0;
        Connection conn = getConnection();
        //sql
        String sql = "insert into admin(admin_username,admin_password) values(?,?)";
        //预编译对象
        PreparedStatement stat = null;
        try {
            //解析
            stat = conn.prepareStatement(sql);
            stat.setObject(1, admin.getAdminAccount());
            stat.setObject(2, admin.getAdminPassword());
            i = stat.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放资源
            release(null, stat, conn);
        }
        //返回对象
        return i;
    }

    @Override
    public MemberAll memberLogin(String memberAccount, String memberPassword) {
        //返回对象
        MemberAll memberAll = null;
        //结果集对象
        ResultSet rs = null;
        //预编译对象
        PreparedStatement stat = null;
        //连接
        Connection conn = this.getConnection();
        //sql
        String sql = "select member.member_id,member_account,member_password,class_id,src,student_score,student_Name,student_sex,coach from member left join coachscore on member.member_id=coachscore.member_id  where member.member_account = ? and member_password = ?";
        try {
            //设置参数
            stat = conn.prepareStatement(sql);
            stat.setObject(1, memberAccount);
            stat.setObject(2, memberPassword);
            rs = stat.executeQuery();
            //解析
            while (rs.next()) {
                memberAll = new MemberAll();
                memberAll.setMemberId(rs.getString(1));
                memberAll.setMemberAccount(rs.getString(2));
                memberAll.setMemberPassword(rs.getString(3));
                memberAll.setClassId(rs.getInt(4));
                memberAll.setSrc(rs.getString(5));
                memberAll.setScore(rs.getInt(6));
                memberAll.setStudentName(rs.getString(7));
                memberAll.setStudentSex(rs.getString(8));
                memberAll.setCoach(rs.getString(9));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            release(rs, stat, conn);
        }
        return memberAll;
    }

    @Override
    public Coach coachLogin(String coachId, String coachPassword) {
        //返回对象
        Coach coach = null;
        //结果集对象
        ResultSet rs = null;
        //预编译对象
        PreparedStatement stat = null;
        //连接
        Connection conn = this.getConnection();
        //sql
        String sql = "select * from coach where coach_id = ? and coach_password = ?";
        try {
            //设置参数
            stat = conn.prepareStatement(sql);
            stat.setObject(1, coachId);
            stat.setString(2, coachPassword);
            rs = stat.executeQuery();
            //解析
            while (rs.next()) {
                coach = new Coach();
                coach.setCoachId(rs.getString(1));
                coach.setCoachPassword(rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            release(rs, stat, conn);
        }
        return coach;
    }
}
