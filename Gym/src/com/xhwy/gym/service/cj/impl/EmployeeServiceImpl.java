package com.xhwy.gym.service.cj.impl;

import com.xhwy.gym.dao.cj.EmployeeDao;
import com.xhwy.gym.dao.cj.impl.EmployeeDaoImpl;
import com.xhwy.gym.entity.Employee;
import com.xhwy.gym.service.cj.EmployeeService;

import java.util.List;

/**
 * @author 陈健
 * @version 1.0
 * @date 2022/7/21 14:48
 */
public class EmployeeServiceImpl implements EmployeeService {
    //创建员工数据访问层
    EmployeeDao employees = new EmployeeDaoImpl();

    @Override
    public List<Object> allEmployee(String page, String limit) {
        //返回查询所有员工
        return employees.allEmployee(page, limit);
    }

    @Override
    public List<Object> allEmployeeLike(String page, String limit, String employeeAccount, String employeeName, String entryTime, String staff) {
        //返回模糊查询所有员工信息
        return employees.allEmployeeLike(page, limit, employeeAccount, employeeName, entryTime, staff);
    }

    @Override
    public int countEmployee() {
        //返回查询员工总条数
        return employees.countEmployee();
    }

    @Override
    public int editEmployee(Employee employee) {
        //返回编辑员工信息
        return employees.editEmployee(employee);
    }

    @Override
    public int addEmployee(Employee employee) {
        //返回添加员工
        return employees.addEmployee(employee);
    }

    @Override
    public int delEmployee(int employee) {
        //返回删除员工
        return employees.delEmployee(employee);
    }
}
