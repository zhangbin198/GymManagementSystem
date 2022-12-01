package com.xhwy.gym.web.servlet.lyy;

import com.xhwy.gym.entity.ClassTable;
import com.xhwy.gym.service.lyy.ClassTableService;
import com.xhwy.gym.service.lyy.impl.ClassTableServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 编辑器材信息数据控制层
 *
 * @author 李缘缘
 * @version 1.0
 * @date 2022/7/20 6:26
 */
@WebServlet("/GymClassTableEditServlets.do")
public class GymClassTableEditServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置请求编码
        req.setCharacterEncoding("UTF-8");
        //设置响应编码
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        //获取参数
        String classId = req.getParameter("classId");
        String className = req.getParameter("className");
        String classBegin = req.getParameter("classBegin");
        String classTime = req.getParameter("classTime");
        String coach = req.getParameter("coach");
        //封装实体类
        ClassTable classTable = new ClassTable();
        classTable.setClassId(Integer.parseInt(classId));
        classTable.setClassName(className);
        classTable.setClassBegin(classBegin);
        classTable.setClassTime(classTime);
        classTable.setCoach(coach);
        //实例化业务层
        ClassTableService classTables = new ClassTableServiceImpl();
        int i = classTables.editClassTable(classTable);
        //判断是否修改成功
        if (i == 1) {
            resp.getWriter().write("success");
        } else {
            resp.getWriter().write("fail");
        }
    }
}
