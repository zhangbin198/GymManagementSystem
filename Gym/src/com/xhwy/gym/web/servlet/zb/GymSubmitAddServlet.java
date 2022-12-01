package com.xhwy.gym.web.servlet.zb;

import com.xhwy.gym.entity.ClassTable;
import com.xhwy.gym.service.lyy.ClassTableService;
import com.xhwy.gym.service.lyy.impl.ClassTableServiceImpl;
import com.xhwy.gym.service.zb.CoachScoreService;
import com.xhwy.gym.service.zb.impl.CoachScoreServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 张斌
 * @version 1.0
 * @date 2022/8/5 21:36
 */
@WebServlet("/GymSubmitAddServlet.do")
public class GymSubmitAddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置请求编码
        req.setCharacterEncoding("UTF-8");
        //设置响应编码
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        //获取参数
        String memberId = req.getParameter("memberId");
        String src = req.getParameter("src");
        //实例化
        CoachScoreService coachScroes = new CoachScoreServiceImpl();
        int i = coachScroes.submitProject(memberId, src);
        //判断是否添加成功
        if (i == 1) {
            resp.getWriter().write("success");
        } else {
            resp.getWriter().write("fail");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
