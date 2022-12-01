package com.xhwy.gym.dao.cj.impl;

import com.xhwy.gym.dao.cj.EmployeeDao;
import com.xhwy.gym.entity.Employee;
import com.xhwy.gym.util.JdbcUtils;
import com.xhwy.gym.util.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 陈健
 * @version 1.0
 * @date 2022/7/21 14:39
 */
public class EmployeeDaoImpl extends JdbcUtils implements EmployeeDao {
    @Override
    public List<Object> allEmployee(String page, String limit) {
        //返回对象
        List<Object> employees = new ArrayList<>();
        //连接
        Connection conn = getConnection();
        //sql
        String sql = " select * from employee  order by employee_account desc limit ?,?";
        //page limit 转int
        int page1 = Integer.parseInt(page);
        int limit1 = Integer.parseInt(limit);
        //返回结果集
        ResultSet rs = exdcuteQuery(sql, (page1 - 1) * limit1, limit1);
        try {
            //解析
            while (rs.next()) {
                Employee employee = new Employee();
                employee.setEmployeeAccount(rs.getInt(1));
                employee.setEmployeeName(rs.getString(2));
                employee.setEmployeeGender(rs.getString(3));
                employee.setEmployeeAge(rs.getInt(4));
                employee.setEntryTime(rs.getString(5));
                employee.setStaff(rs.getString(6));
                employee.setEmployeeMessage(rs.getString(7));
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //关闭
            release(rs, null, conn);
        }
        //返回对象
        return employees;
    }

    @Override
    public List<Object> allEmployeeLike(String page, String limit, String employeeAccount, String employeeName, String entryTime, String staff) {
        //返回对象
        List<Object> employees = new ArrayList<>();
        //连接
        Connection conn = getConnection();
        //sql
        String sql = " select * from employee " +
                " where employee_account like '%" + employeeAccount + "%' and employee_name like '%" + employeeName + "%' and entry_time like '%" + entryTime + "%' and staff like '%" + staff + "%' " +
                " order by employee_account desc limit ?,?";
        //page,limit转int
        int page1 = Integer.parseInt(page);
        int limit1 = Integer.parseInt(limit);
        //返回结果集对象
        ResultSet rs = exdcuteQuery(sql, (page1 - 1) * limit1, limit1);
        try {
            //解析
            while (rs.next()) {
                Employee employee = new Employee();
                employee.setEmployeeAccount(rs.getInt(1));
                employee.setEmployeeName(rs.getString(2));
                employee.setEmployeeGender(rs.getString(3));
                employee.setEmployeeAge(rs.getInt(4));
                employee.setEntryTime(rs.getString(5));
                employee.setStaff(rs.getString(6));
                employee.setEmployeeMessage(rs.getString(7));
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //释放资源
            release(rs, null, conn);
        }
        return employees;
    }

    @Override
    public int countEmployee() {
        //返回对象
        int cnt = 0;
        //连接
        Connection conn = getConnection();
        //sql
        String sql = "select count(*) from employee";
        //返回结果集对象
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
    public int editEmployee(Employee employee) {
        //返回对象
        int i = 0;
        //连接
        Connection conn = getConnection();
        //sql
        String sql = "update employee set employee_name=?,employee_gender=?,employee_age=?,entry_time=?,staff=?,employee_message=? " +
                " where employee_account=?";
        //预编译对象
        PreparedStatement stat = null;
        try {
            //编译得到
            stat = conn.prepareStatement(sql);
            //解析
            stat.setObject(1, employee.getEmployeeName());
            stat.setObject(2, employee.getEmployeeGender());
            stat.setObject(3, employee.getEmployeeAge());
            stat.setObject(4, employee.getEntryTime());
            stat.setObject(5, employee.getStaff());
            stat.setObject(6, employee.getEmployeeMessage());
            stat.setObject(7, employee.getEmployeeAccount());
            //受影响行数
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
    public int addEmployee(Employee employee) {
        //返回对象
        int i = 0;
        Connection conn = getConnection();
        //sql
        String sql = "insert into employee(employee_name,employee_gender,employee_age,entry_time,staff,employee_message)values(?,?,?,?,?,?)";
        //预编译对象
        PreparedStatement stat = null;
        try {
            //编译得到
            stat = conn.prepareStatement(sql);
            //解析
            stat.setObject(1, employee.getEmployeeName());
            stat.setObject(2, employee.getEmployeeGender());
            stat.setObject(3, employee.getEmployeeAge());
            stat.setObject(4, employee.getEntryTime());
            stat.setObject(5, employee.getStaff());
            stat.setObject(6, employee.getEmployeeMessage());
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
    public int delEmployee(int employee) {
        //返回对象
        int i = 0;
        //连接
        Connection conn = getConnection();
        //sql
        String sql = "delete from employee where employee_account = ?";
        //预编译对象
        PreparedStatement stat = null;
        try {
            //编译得到
            stat = conn.prepareStatement(sql);
            //解析
            stat.setObject(1, employee);
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
