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
 * 学员选课数据控制层
 *
 * @author 张斌
 * @version 1.0
 * @date 2022/8/4 11:42
 */
@WebServlet("/GymClassTableChoose.do")
public class GymClassTableChooseServlet extends HttpServlet {
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
        //暂无选题,提示选题成功
        System.out.println(a);
        if (a != 0) {
            resp.getWriter().write("选题失败!您已有选题:" + a);
        } else {
            int b = classTables.chooseTitle(classIds, coach, memberId);
            System.out.println(b);
            if (b == 1) {
                resp.getWriter().write("选题成功!选题:" + classIds);
            }
        }
    }
}
