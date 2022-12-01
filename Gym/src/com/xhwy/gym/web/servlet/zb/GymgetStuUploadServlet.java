package com.xhwy.gym.web.servlet.zb;

import com.alibaba.fastjson.JSONObject;
import com.xhwy.gym.entity.Vo;
import com.xhwy.gym.service.zb.CoachScoreService;
import com.xhwy.gym.service.zb.impl.CoachScoreServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author 张斌
 * @version 1.0
 * @date 2022/8/6 1:53
 */
@WebServlet("/GymgetStuUpload.do")
public class GymgetStuUploadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置请求编码
        req.setCharacterEncoding("UTF-8");
        //设置响应编码
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        //获取分页参数
        String coachId = req.getParameter("coachId");
        String pageStr = req.getParameter("page");
        String limitStr = req.getParameter("limit");
        //实例化业务层
        CoachScoreService coachScores = new CoachScoreServiceImpl();
        List<Object> list = coachScores.allCoach(pageStr, limitStr, coachId);
        //封装与ajax打交道的实体类
        Vo vo = new Vo();
        vo.setCode(0);//解析接口状态
        vo.setMsg("success");//解析提示文本
        vo.setCount(coachScores.countCoachScore(coachId));//解析数据长度
        vo.setData(list);//解析数据列表
        resp.getWriter().write(JSONObject.toJSONString(vo));//list集合转json
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
