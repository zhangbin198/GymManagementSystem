package com.xhwy.gym.web.servlet.cj;

import com.alibaba.fastjson.JSONObject;
import com.xhwy.gym.entity.Vo;
import com.xhwy.gym.service.cj.EmployeeService;
import com.xhwy.gym.service.cj.impl.EmployeeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 模糊查询员工信息控制层
 *
 * @author 张斌
 * @version 1.0
 * @date 2022/7/21 23:32
 */
@WebServlet("/GymEmployeeLikeSelectServlet.do")
public class GymEmployeeLikeSelectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置请求编码
        req.setCharacterEncoding("UTF-8");
        //设置响应编码
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        //获取分页参数
        String pageStr = req.getParameter("page");
        String limitStr = req.getParameter("limit");
        //获取模糊查询的参数
        String employeeAccountStr = req.getParameter("employeeAccount");
        String employeeNameStr = req.getParameter("employeeName");
        String entryTimeStr = req.getParameter("entryTime");
        String staffStr = req.getParameter("staff");
        //实例化业务层
        EmployeeService employees = new EmployeeServiceImpl();
        List<Object> list = employees.allEmployeeLike(pageStr, limitStr, employeeAccountStr, employeeNameStr, entryTimeStr, staffStr);
        //封装与ajax打交道的实体类
        Vo vo = new Vo();
        vo.setCode(0);//解析接口状态
        vo.setMsg("success");//解析提示文本
        vo.setCount(employees.countEmployee());//解析数据长度
        vo.setData(list);//解析数据列表
        resp.getWriter().write(JSONObject.toJSONString(vo));//list集合转json
    }
}
