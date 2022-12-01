package com.xhwy.gym.web.servlet.zb;

import com.xhwy.gym.entity.Admin;
import com.xhwy.gym.service.zb.LoginService;
import com.xhwy.gym.service.zb.impl.LoginServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 添加管理员数据控制层
 *
 * @author 张斌
 * @version 1.0
 * @date 2022/7/18 16:41
 */
@WebServlet("/GymAddLogin.do")
public class GymLoginAddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置请求编码
        req.setCharacterEncoding("UTF-8");
        //设置响应编码
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        //获取参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String passwords = req.getParameter("passwords");
        //封装实体类
        Admin admin = new Admin();
        admin.setAdminAccount(Integer.parseInt(username));
        admin.setAdminPassword(password);
        //实例化业务层
        LoginService gym = new LoginServiceImpl();
        //判断是否添加成功
        int i = gym.addLogin(admin);
        if (i == 1) {
            resp.getWriter().write("success");
        } else {
            resp.getWriter().write("fail");
        }
    }
}
