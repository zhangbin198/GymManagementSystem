package com.xhwy.gym.web.servlet.lyy;

import com.xhwy.gym.service.lyy.ClassTableService;
import com.xhwy.gym.service.lyy.impl.ClassTableServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 根据编号删除指定器材
 *
 * @author 李缘缘
 * @version 1.0
 * @date 2022/7/20 14:42
 */
@WebServlet("/GymClassTableDelServlet.do")
public class GymClassTableDelServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置请求编码
        req.setCharacterEncoding("UTF-8");
        //设置响应编码
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        //获取删除id参数
        String classId = req.getParameter("classId");
        //实例化业务层
        ClassTableService classTables = new ClassTableServiceImpl();
        int i = classTables.delClassTable(Integer.parseInt(classId));
        //判断是否删除成功
        if (i == 1) {
            resp.getWriter().write("success");
        } else {
            resp.getWriter().write("fail");
        }
    }
}
