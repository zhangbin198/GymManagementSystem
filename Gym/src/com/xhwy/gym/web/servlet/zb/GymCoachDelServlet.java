package com.xhwy.gym.web.servlet.zb;

import com.xhwy.gym.service.zb.CoachService;
import com.xhwy.gym.service.zb.impl.CoachServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 张斌
 * @version 1.0
 * @date 2022/8/1 20:16
 */
@WebServlet("/GymCoachDel.do")
public class GymCoachDelServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置请求编码
        req.setCharacterEncoding("UTF-8");
        //设置响应编码
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        //获取删除id参数
        String coach = req.getParameter("coachId");
        //实例化业务层
        CoachService coachs = new CoachServiceImpl();
        int i = coachs.delCoach(Integer.parseInt(coach));
        //判断是否删除成功
        if (i == 1) {
            resp.getWriter().write("success");
        } else {
            resp.getWriter().write("fail");
        }
    }
}
