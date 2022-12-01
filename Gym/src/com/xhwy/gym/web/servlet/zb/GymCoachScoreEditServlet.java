package com.xhwy.gym.web.servlet.zb;

import com.xhwy.gym.entity.CoachScore;
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
 * @date 2022/8/3 13:52
 */
@WebServlet("/GymCoachScoreEdit.do")
public class GymCoachScoreEditServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置请求编码
        req.setCharacterEncoding("UTF-8");
        //设置响应编码
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        //获取参数
        String memberId = req.getParameter("memberId");
        String studentName = req.getParameter("studentName");
        String studentSex = req.getParameter("studentSex");
        String coach = req.getParameter("coach");
        String studentScore = req.getParameter("studentScore");
        //封装实体类
        CoachScore coachScore = new CoachScore();
        coachScore.setMemberId(memberId);
        coachScore.setStudentName(studentName);
        coachScore.setStudentSex(studentSex);
        coachScore.setCoach(coach);
        coachScore.setStudentScore(studentScore);
        //实例化业务层
        CoachScoreService coachScores = new CoachScoreServiceImpl();
        int i = coachScores.editCoachScore(coachScore);
        //判断是否编辑成功
        if (i == 1) {
            resp.getWriter().write("success");
        } else {
            resp.getWriter().write("fail");
        }
    }
}
