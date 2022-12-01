package com.xhwy.gym.web.servlet.skc;

import com.xhwy.gym.entity.ClassOrder;
import com.xhwy.gym.service.skc.ClassOderService;
import com.xhwy.gym.service.skc.impl.ClassOderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 添加报名信息
 *
 * @author 佘柯澂
 * @version 1.0
 * @date 2022/7/20 11:32
 */
@WebServlet("/GymClassOrderAddServlet.do")
public class GymClassOrderAddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置请求编码
        req.setCharacterEncoding("UTF-8");
        //设置响应编码
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        //获取参数
        String className = req.getParameter("className");
        String classPhone = req.getParameter("classPhone");
        String classTable = req.getParameter("classTable");
        String classCoach = req.getParameter("classCoach");
        //封装实体类
        ClassOrder classOrder = new ClassOrder();
        classOrder.setClassName(className);
        classOrder.setClassPhone(classPhone);
        classOrder.setClassTable(classTable);
        classOrder.setClassCoach(classCoach);
        //实例化业务层
        ClassOderService classOrders = new ClassOderServiceImpl();
        int i = classOrders.addClassOrder(classOrder);
        //判断是否添加成功
        if (i == 1) {
            resp.getWriter().write("success");
        } else {
            resp.getWriter().write("fail");
        }
    }
}
