package com.xhwy.gym.web.servlet.zb;

import com.xhwy.gym.entity.Coach;
import com.xhwy.gym.service.zb.CoachService;
import com.xhwy.gym.service.zb.impl.CoachServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 编辑教练信息控制层
 *
 * @author 张斌
 * @version 1.0
 * @date 2022/8/1 20:09
 */
@WebServlet("/GymCoachEdit.do")
public class GymCoachEditServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置请求编码
        req.setCharacterEncoding("UTF-8");
        //设置响应编码
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        //获取参数
        String coachId = req.getParameter("coachId");
        String coachName = req.getParameter("coachName");
        String coachGender = req.getParameter("coachGender");
        String coachAge = req.getParameter("coachAge");
        String coachField = req.getParameter("coachField");
        String coachSaff = req.getParameter("coachSaff");
        //封装实体类
        Coach coach = new Coach();
        coach.setCoachId(coachId);
        coach.setCoachName(coachName);
        coach.setCoachGender(coachGender);
        coach.setCoachAge(coachAge);
        coach.setCoachField(coachField);
        coach.setCoachSaff(coachSaff);
        //实例化业务层
        CoachService coachs = new CoachServiceImpl();
        int i = coachs.editCoach(coach);
        //判断是否编辑成功
        if (i == 1) {
            resp.getWriter().write("success");
        } else {
            resp.getWriter().write("fail");
        }
    }
}
