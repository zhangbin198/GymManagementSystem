package com.xhwy.gym.web.servlet.cj;

import com.xhwy.gym.service.cj.EmployeeService;
import com.xhwy.gym.service.cj.impl.EmployeeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 根据账号删除指定员工控制层
 *
 * @author 陈健
 * @version 1.0
 * @date 2022/7/20 14:42
 */
@WebServlet("/GymEmployeeDelServlet.do")
public class GymEmployeeDelServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置请求编码
        req.setCharacterEncoding("UTF-8");
        //设置响应编码
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        //获取参数
        String employeeAccount = req.getParameter("employeeAccount");
        //实例化业务层
        EmployeeService employees = new EmployeeServiceImpl();
        int i = employees.delEmployee(Integer.parseInt(employeeAccount));
        //判断是否删除成功
        if (i == 1) {
            resp.getWriter().write("success");
        } else {
            resp.getWriter().write("fail");
        }
    }
}
