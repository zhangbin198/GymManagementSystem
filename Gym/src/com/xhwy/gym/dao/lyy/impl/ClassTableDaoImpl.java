package com.xhwy.gym.dao.lyy.impl;

import com.xhwy.gym.dao.lyy.ClassTableDao;
import com.xhwy.gym.entity.ClassTable;
import com.xhwy.gym.service.lyy.ClassTableService;
import com.xhwy.gym.service.lyy.impl.ClassTableServiceImpl;
import com.xhwy.gym.util.JdbcUtils;
import com.xhwy.gym.util.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 李缘缘
 * @version 1.0
 * @date 2022/7/21 9:38
 */
public class ClassTableDaoImpl extends JdbcUtils implements ClassTableDao {
    @Override
    public List<Object> allClassTable(String page, String limit) {
        //返回对象
        List<Object> classTables = new ArrayList<>();
        //连接对象
        Connection conn = getConnection();
        //sql语句
        String sql = "select * from class_table order by class_id desc limit ?,?";
        //page,limit 转对象
        int page1 = Integer.parseInt(page);
        int limit1 = Integer.parseInt(limit);
        //返回结果集对象
        ResultSet rs = exdcuteQuery(sql, (page1 - 1) * limit1, limit1);
        try {
            //解析
            while (rs.next()) {
                ClassTable classTable = new ClassTable();
                classTable.setClassId(rs.getInt(1));
                classTable.setClassName(rs.getString(2));
                classTable.setClassSrc(rs.getString(3));
                classTable.setClassBegin(rs.getString(4));
                classTable.setClassTime(rs.getString(5));
                classTable.setCoach(rs.getString(6));
                classTables.add(classTable);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //解析资源
            release(rs, null, conn);
        }
        //返回对象
        return classTables;
    }

    @Override
    public List<Object> allClassTableLike(String page, String limit, String classId, String className, String classBegin, String coach) {
        //返回对象
        List<Object> classTables = new ArrayList<>();
        //连接
        Connection conn = getConnection();
        //sql
        String sql = " select * from class_table " +
                " where class_id like '%" + classId + "%' and class_name like '%" + className + "%' and class_begin like '%" + classBegin + "%' and coach like '%" + coach + "%'" +
                " order by class_id desc limit ?,?";
        //page,limit转int
        int page1 = Integer.parseInt(page);
        int limit1 = Integer.parseInt(limit);
        //返回结果集对象
        ResultSet rs = exdcuteQuery(sql, (page1 - 1) * limit1, limit1);
        try {
            //解析
            while (rs.next()) {
                ClassTable classTable = new ClassTable();
                classTable.setClassId(rs.getInt(1));
                classTable.setClassName(rs.getString(2));
                classTable.setClassSrc(rs.getString(3));
                classTable.setClassBegin(rs.getString(4));
                classTable.setClassTime(rs.getString(5));
                classTable.setCoach(rs.getString(6));
                classTables.add(classTable);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //释放资源
            release(rs, null, conn);
        }
        //返回对象
        return classTables;
    }

    @Override
    public int countClassTable() {
        //返回对象
        int cnt = 0;
        //连接对象
        Connection conn = getConnection();
        //sql
        String sql = "select count(*) from class_table";
        //返回结果集对象
        ResultSet rs = this.exdcuteQuery(sql);
        try {
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
    public int editClassTable(ClassTable classTable) {
        //返回对象
        int i = 0;
        Connection conn = getConnection();
        //sql
        String sql = "update class_table set class_name=?,class_begin=?,class_time=?,coach=? " +
                " where class_id=?";
        //预编译对象
        PreparedStatement stat = null;
        try {
            //解析
            stat = conn.prepareStatement(sql);
            stat.setObject(1, classTable.getClassName());
            stat.setObject(2, classTable.getClassBegin());
            stat.setObject(3, classTable.getClassTime());
            stat.setObject(4, classTable.getCoach());
            stat.setObject(5, classTable.getClassId());
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
    public int addClassTable(ClassTable classTable) {
        //返回对象
        int i = 0;
        Connection conn = getConnection();
        //sql
        String sql = "insert into class_table(class_name,class_src,class_begin,class_time,coach)values(?,?,?,?,?)";
        //预编译对象
        PreparedStatement stat = null;
        try {
            //解析
            stat = conn.prepareStatement(sql);
            stat.setObject(1, classTable.getClassName());
            stat.setObject(2, classTable.getClassSrc());
            stat.setObject(3, classTable.getClassBegin());
            stat.setObject(4, classTable.getClassTime());
            stat.setObject(5, classTable.getCoach());
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
    public int delClassTable(int classTable) {
        //返回对象
        int i = 0;
        Connection conn = getConnection();
        //sql
        String sql = "delete from class_table where class_id = ?";
        //预编译对象
        PreparedStatement stat = null;
        try {
            //解析
            stat = conn.prepareStatement(sql);
            stat.setObject(1, classTable);
            //返回受影响行数
            i = stat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //释放资源
            release(null, stat, conn);
        }
        //返回
        return i;
    }

    @Override
    public int ifHasMember(String memberId) {
        int i = 0;
        //连接
        Connection conn = getConnection();
        //sql
        String sql = "select class_id from coachscore where member_id=?";
        PreparedStatement stat = null;
        //返回结果集对象
        ResultSet rs = null;
        try {
            stat = conn.prepareStatement(sql);
            stat.setObject(1, memberId);
            rs = stat.executeQuery();
            //解析
            while (rs.next()) {
                i = rs.getInt("class_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //释放资源
            release(rs, null, conn);
        }
        //返回对象
        return i;
    }

    @Override
    public int chooseTitle(int classId, String coach, String memberId) {
        //返回对象
        int i = 0;
        //连接
        Connection conn = getConnection();
        //sql
        String sql = "update coachscore set class_id=?,coach=? where member_id=?";
        //预编译对象
        PreparedStatement stat = null;
        try {
            //解析
            stat = conn.prepareStatement(sql);
            stat.setInt(1, classId);
            stat.setString(2, coach);
            stat.setString(3, memberId);
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
    public int delTitle(int classId, String coach, String memberId) {
        //返回对象
        int i = 0;
        //连接
        Connection conn = getConnection();
        //sql
        String sql = "update coachscore set class_id=0,coach='无' where member_id=?";
        //预编译对象
        PreparedStatement stat = null;
        try {
            //解析
            stat = conn.prepareStatement(sql);
            stat.setObject(1, memberId);
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
    public String getTName(int classId) {
        //连接对象
        Connection conn = getConnection();
        //sql语句
        String sql = " select class_Name from coachscore where class_id = ?";
        //返回结果集对象
        ResultSet rs = exdcuteQuery(sql, classId);
        try {
            //解析
            while (rs.next()) {
                return rs.getString("class_Name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //解析资源
            release(rs, null, conn);
        }
        //返回对象
        return null;
    }
}
