package com.xhwy.gym.web.servlet.cj;

import com.xhwy.gym.entity.Employee;
import com.xhwy.gym.service.cj.EmployeeService;
import com.xhwy.gym.service.cj.impl.EmployeeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 添加员工信息控制层
 *
 * @author 陈健
 * @version 1.0
 * @date 2022/7/20 11:32
 */
@WebServlet("/GymEmployeeAddServlet.do")
public class GymEmployeeAddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置请求编码
        req.setCharacterEncoding("UTF-8");
        //设置响应编码
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        //获取参数
        String employeeName = req.getParameter("employeeName");
        String employeeGender = req.getParameter("employeeGender");
        String employeeAge = req.getParameter("employeeAge");
        String entryTime = req.getParameter("entryTime");
        String staff = req.getParameter("staff");
        String employeeMessage = req.getParameter("employeeMessage");
        //封装实体类
        Employee employee = new Employee();
        employee.setEmployeeName(employeeName);
        employee.setEmployeeGender(employeeGender);
        employee.setEmployeeAge(Integer.parseInt(employeeAge));
        employee.setEntryTime(entryTime);
        employee.setStaff(staff);
        employee.setEmployeeMessage(employeeMessage);
        //实例化业务层
        EmployeeService employees = new EmployeeServiceImpl();
        int i = employees.addEmployee(employee);
        //判断是否添加成功
        if (i == 1) {
            resp.getWriter().write("success");
        } else {
            resp.getWriter().write("fail");
        }
    }
}
