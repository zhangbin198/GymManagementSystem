package com.xhwy.gym.dao.zb.impl;

import com.xhwy.gym.dao.zb.CoachScoreDao;
import com.xhwy.gym.entity.Coach;
import com.xhwy.gym.entity.CoachScore;
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
 * @date 2022/8/3 13:50
 */
public class CoachScoreDaoImpl extends JdbcUtils implements CoachScoreDao {
    @Override
    public List<Object> allCoach(String page, String limit, String coachId) {
        //返回对象
        List<Object> coachScores = new ArrayList<>();
        //连接
        Connection conn = getConnection();
        //sql
        String sql = "select * from coachscore where coach = ?  limit ?,?";
        //page,limit转int
        int page1 = Integer.parseInt(page);
        int limit1 = Integer.parseInt(limit);
        //结果集对象
        ResultSet rs = exdcuteQuery(sql, coachId, (page1 - 1) * limit1, limit1);
        try {
            //解析
            while (rs.next()) {
                CoachScore coachScore = new CoachScore();
                coachScore.setMemberId(rs.getString(1));
                coachScore.setStudentName(rs.getString(2));
                coachScore.setClassId(rs.getString(3));
                coachScore.setStudentSex(rs.getString(4));
                coachScore.setCoach(rs.getString(5));
                coachScore.setStudentScore(rs.getString(6));
                coachScore.setStudentClassName(rs.getString(7));
                coachScore.setSrc(rs.getString(8));
                //添加到集合
                coachScores.add(coachScore);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //释放资源
            release(rs, null, conn);
        }
        //返回对象
        return coachScores;
    }

    @Override
    public List<Object> allCoachScore(String page, String limit, String studentId, String studentName, String coachId, String studentScore) {
        //返回对象
        List<Object> coachScores = new ArrayList<>();
        //连接
        Connection conn = getConnection();
        //sql
        String sql = " select * from coachscore " +
                " where member_id like '%" + studentId + "%' and student_Name  like '%" + studentName + "%' and " +
                " coach like '%" + coachId + "%' and student_score like '%" + studentScore + "%' and coach=?" +
                " order by member_id desc limit ?,?";
        //page,limit转int
        int page1 = Integer.parseInt(page);
        int limit1 = Integer.parseInt(limit);
        //结果集对象
        ResultSet rs = exdcuteQuery(sql, coachId, (page1 - 1) * limit1, limit1);
        try {
            //解析
            while (rs.next()) {
                CoachScore coachScore = new CoachScore();
                coachScore.setMemberId(rs.getString(1));
                coachScore.setStudentName(rs.getString(2));
                coachScore.setClassId(rs.getString(3));
                coachScore.setStudentSex(rs.getString(4));
                coachScore.setCoach(rs.getString(5));
                coachScore.setStudentScore(rs.getString(6));
                coachScore.setStudentClassName(rs.getString(7));
                coachScore.setSrc(rs.getString(8));
                //添加到集合
                coachScores.add(coachScore);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //释放资源
            release(rs, null, conn);
        }
        //返回对象
        return coachScores;
    }

    @Override
    public int countCoachScore(String coachId) {
        //返回对象
        int cnt = 0;
        //连接
        Connection conn = getConnection();
        //sql
        String sql = "select count(*) from coachscore where coach = ?";
        //结果集对象
        ResultSet rs = this.exdcuteQuery(sql, coachId);
        ;
        try {
            while (rs.next()) {
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
    public int editCoachScore(CoachScore coachScore) {
        //返回对象
        int i = 0;
        //连接
        Connection conn = getConnection();
        String sql = "update coachscore set student_Name=?,student_sex=?,coach=?,student_score=?" +
                " where member_id=?";
        //预编译对象
        PreparedStatement stat = null;
        try {
            //解析
            stat = conn.prepareStatement(sql);
            stat.setObject(1, coachScore.getStudentName());
            stat.setObject(2, coachScore.getStudentSex());
            stat.setObject(3, coachScore.getCoach());
            stat.setObject(4, coachScore.getStudentScore());
            stat.setObject(5, coachScore.getMemberId());
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
    public int submitProject(String memberId, String src) {
        //返回对象
        int i = 0;
        //连接
        Connection conn = getConnection();
        String sql = "update coachscore set src = ?" +
                " where member_id=?";

        //预编译对象
        PreparedStatement stat = null;
        try {
            //解析
            stat = conn.prepareStatement(sql);
            stat.setObject(1, src);
            stat.setObject(2, memberId);
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
}
