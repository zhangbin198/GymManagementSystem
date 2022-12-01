package com.xhwy.gym.dao.zb.impl;

import com.xhwy.gym.dao.zb.CoachDao;
import com.xhwy.gym.entity.Coach;
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
 * @date 2022/8/1 19:44
 */
public class CoachDaoImpl extends JdbcUtils implements CoachDao {
    @Override
    public List<Object> allCoach(String page, String limit) {
        //返回对象
        List<Object> Coachs = new ArrayList<>();
        //连接
        Connection conn = getConnection();
        //sql
        String sql = " select * from coach order by coach_id desc limit ?,?";
        //page,limit转int
        int page1 = Integer.parseInt(page);
        int limit1 = Integer.parseInt(limit);
        //结果集对象
        ResultSet rs = exdcuteQuery(sql, (page1 - 1) * limit1, limit1);
        try {
            //解析
            while (rs.next()) {
                Coach coach = new Coach();
                coach.setCoachId(rs.getString(1));
                coach.setCoachPassword(rs.getString(2));
                coach.setCoachName(rs.getString(3));
                coach.setCoachGender(rs.getString(4));
                coach.setCoachAge(rs.getString(5));
                coach.setCoachField(rs.getString(6));
                coach.setCoachSaff(rs.getString(7));
                //添加到集合
                Coachs.add(coach);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //释放资源
            release(rs, null, conn);
        }
        //返回对象
        return Coachs;
    }

    @Override
    public List<Object> allCoachLike(String page, String limit, String CoachId, String CoachName, String coachField, String coachSaff) {
        //返回对象
        List<Object> Coachs = new ArrayList<>();
        //连接
        Connection conn = getConnection();
        //sql
        String sql = " select * from coach " +
                " where coach_id like '%" + CoachId + "%' and coach_Name  like '%" + CoachName + "%' and coach_field like '%" + coachField + "%' and coach_saff like '%" + coachSaff + "%' " +
                " order by coach_id desc limit ?,?";
        //page,limit转int
        int page1 = Integer.parseInt(page);
        int limit1 = Integer.parseInt(limit);
        //结果集对象
        ResultSet rs = exdcuteQuery(sql, (page1 - 1) * limit1, limit1);
        try {
            //解析
            while (rs.next()) {
                Coach coach = new Coach();
                coach.setCoachId(rs.getString(1));
                coach.setCoachPassword(rs.getString(2));
                coach.setCoachName(rs.getString(3));
                coach.setCoachGender(rs.getString(4));
                coach.setCoachAge(rs.getString(5));
                coach.setCoachField(rs.getString(6));
                coach.setCoachSaff(rs.getString(7));
                //添加到集合
                Coachs.add(coach);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //释放资源
            release(rs, null, conn);
        }
        //返回对象
        return Coachs;
    }

    @Override
    public int countCoach() {
        //返回对象
        int cnt = 0;
        //连接
        Connection conn = getConnection();
        //sql
        String sql = "select count(*) from coach";
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
    public int editCoach(Coach coach) {
        //返回对象
        int i = 0;
        //连接
        Connection conn = getConnection();
        String sql = "update coach set coach_Name=?,coach_gender=?,coach_age=?,coach_field=?,coach_saff=?" +
                " where coach_id=?";
        //预编译对象
        PreparedStatement stat = null;
        try {
            //解析
            stat = conn.prepareStatement(sql);
            stat.setObject(1, coach.getCoachId());
            stat.setObject(2, coach.getCoachName());
            stat.setObject(3, coach.getCoachGender());
            stat.setObject(4, coach.getCoachAge());
            stat.setObject(5, coach.getCoachField());
            stat.setObject(6, coach.getCoachSaff());
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
    public int addCoach(Coach coach) {
        //返回对象
        int i = 0;
        //连接
        Connection conn = getConnection();
        //sql
        String sql = "insert into coach(coach_Name,coach_gender,coach_age," +
                " coach_field,coach_saff)" +
                " values(?,?,?,?,?)";
        //预先编译对象
        PreparedStatement stat = null;
        try {
            //解析
            stat = conn.prepareStatement(sql);
            stat.setObject(1, coach.getCoachName());
            stat.setObject(2, coach.getCoachGender());
            stat.setObject(3, coach.getCoachAge());
            stat.setObject(4, coach.getCoachField());
            stat.setObject(5, coach.getCoachSaff());
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
    public int delCoach(int coach) {
        //返回对象
        int i = 0;
        //连接
        Connection conn = getConnection();
        //sql
        String sql = "delete from coach where coach_id = ?";
        //预编译对象
        PreparedStatement stat = null;
        try {
            //解析
            stat = conn.prepareStatement(sql);
            stat.setObject(1, coach);
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
