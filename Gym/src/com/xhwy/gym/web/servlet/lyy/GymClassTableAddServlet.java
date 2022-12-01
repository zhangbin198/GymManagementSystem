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
 * 添加器材信息
 *
 * @author 李缘缘
 * @version 1.0
 * @date 2022/7/20 11:32
 */
@WebServlet("/GymClassTableAddServlet.do")
public class GymClassTableAddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置请求编码
        req.setCharacterEncoding("utf-8");
        //设置响应编码
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        //获取参数
        String className = req.getParameter("className");
        String classSrc = req.getParameter("classSrc");
        String classBegin = req.getParameter("classBegin");
        String classTime = req.getParameter("classTime");
        String coachName = req.getParameter("coachName");
        //封装实体类
        ClassTable classTable = new ClassTable();
        classTable.setClassName(className);
        classTable.setClassSrc(classSrc);
        classTable.setClassBegin(classBegin);
        classTable.setClassTime(classTime);
        classTable.setCoach(coachName);
        //实例化
        ClassTableService classTables = new ClassTableServiceImpl();
        int i = classTables.addClassTable(classTable);
        //判断是否添加成功
        if (i == 1) {
            resp.getWriter().write("success");
        } else {
            resp.getWriter().write("fail");
        }
    }
}
