package com.xhwy.gym.service.cj;

import java.util.List;

/**
 * @author 陈健
 * @version 1.0
 * @date 2022/7/21 14:47
 */
public interface EmployeeService {
    /**
     * 查询所有数据并分页
     *
     * @param page  第几页
     * @param limit 每页显示多少条数据
     * @return 查询返回的数据集合
     */
    List<Object> allEmployee(String page, String limit);

    /**
     * 模糊查询器材信息
     *
     * @param page            第几页
     * @param limit           每页显示多少条数据
     * @param employeeAccount 员工编号
     * @param employeeName    员工名称
     * @param entryTime       入职时间
     * @param staff           职务
     * @return 返回模糊查询到的员工信息
     */
    List<Object> allEmployeeLike(String page, String limit, String employeeAccount, String employeeName, String entryTime, String staff);

    /**
     * 获取员工信息总和
     *
     * @return 返回课程信息总和
     */
    int countEmployee();

    /**
     * 编辑员工信息
     *
     * @param employee 员工信息对象
     * @return 返回员工信息
     */
    int editEmployee(com.xhwy.gym.entity.Employee employee);

    /**
     * 添加员工信息
     *
     * @param employee 员工信息对象
     * @return 返回受影响行数
     */
    int addEmployee(com.xhwy.gym.entity.Employee employee);

    /**
     * 根据编号删除指定员工信息
     *
     * @param employee 员工对象
     * @return 返回受影响行数
     */
    int delEmployee(int employee);
}
