package com.xhwy.gym.web.servlet.zb;

import com.xhwy.gym.service.lyy.ClassTableService;
import com.xhwy.gym.service.lyy.impl.ClassTableServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 张斌
 * @version 1.0
 * @date 2022/8/5 10:12
 */
@WebServlet("/GymClassTableDel.do")
public class GymClassTableDelServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置请求编码
        req.setCharacterEncoding("UTF-8");
        //设置响应编码
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        //获取参数
        String classId = req.getParameter("classId");
        String coach = req.getParameter("coach");
        String memberId = req.getParameter("memberId");
        int classIds = Integer.parseInt(classId);
        //实例化业务层
        ClassTableService classTables = new ClassTableServiceImpl();
        int a = classTables.ifHasMember(memberId);
        if (a != 0) {
            if (a == classIds) {
                //执行退选操作
                int b = classTables.delTitle(classIds, coach, memberId);
                if (b == 1) {
                    resp.getWriter().write("退选成功,请尽快重新选择");
                }
            } else {
                //提示当前您的选题不是这个,无效点击
                resp.getWriter().write("当前您的选题不是这个,无效点击");
            }
        } else {
            //没有选课 提示先进行选课
            resp.getWriter().write("您当前还未选择课程,请先选择一门课程进行训练!");
        }
    }
}
