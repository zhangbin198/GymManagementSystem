package com.xhwy.gym.web.servlet.skc;

import com.xhwy.gym.service.skc.ClassOderService;
import com.xhwy.gym.service.skc.impl.ClassOderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 根据编号删除指定报名
 *
 * @author 佘柯澂
 * @version 1.0
 * @date 2022/7/20 14:42
 */
@WebServlet("/GymClassOrderDelServlet.do")
public class GymClassOrderDelServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置请求编码
        req.setCharacterEncoding("UTF-8");
        //设置响应编码
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        //获取删除id参数
        String classOrderId = req.getParameter("classOrderId");
        //实例化业务层
        ClassOderService classOrders = new ClassOderServiceImpl();
        int i = classOrders.delClassOrder(Integer.parseInt(classOrderId));
        //判断是否删除成功
        if (i == 1) {
            resp.getWriter().write("success");
        }
    }
}
