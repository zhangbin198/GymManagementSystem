package com.xhwy.gym.web.servlet.zb;

import com.xhwy.gym.entity.Admin;
import com.xhwy.gym.entity.Coach;
import com.xhwy.gym.entity.MemberAll;
import com.xhwy.gym.service.zb.LoginService;
import com.xhwy.gym.service.zb.MemberService;
import com.xhwy.gym.service.zb.impl.LoginServiceImpl;
import com.xhwy.gym.service.zb.impl.MemberServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author 张斌
 * 使用验证码登录判断是否登录成功
 * @version 1.0
 * @date 2022/7/15 0:53
 */
@WebServlet("/codeLogin.do")
public class CodeLoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码 -->过滤器处理过
        //获取参数
        String code = req.getParameter("code");//传过的验证码
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String entity = req.getParameter("entity");
        //实例化业务层
        LoginService gym = new LoginServiceImpl();
        Admin uname = gym.login(username, password);
        MemberAll members = gym.memberLogin(username, password);
        System.out.println(members);
        Coach coachs = gym.coachLogin(username, password);
        //输出对象
        PrintWriter out = resp.getWriter();
        //获取会话中共享的code
        HttpSession session = req.getSession();
        String sessionCode = (String) session.getAttribute("code");
        session.setAttribute("uname", uname);
        session.setAttribute("members", members);
        session.setAttribute("coachs", coachs);
        //判断是否登录成功
        if (uname != null && entity.equals("管理员") && sessionCode.equalsIgnoreCase(code)) {
            out.println("<script>alert('登录成功');location.href='admin.html'</script>");
        } else if (members != null && entity.equals("会员") && sessionCode.equalsIgnoreCase(code)) {

            out.println("<script>alert('登录成功');location.href='member.html'</script>");
        } else if (coachs != null && entity.equals("教练") && sessionCode.equalsIgnoreCase(code)) {
            out.println("<script>alert('登录成功');location.href='coach.html'</script>");
        } else {
            out.println("<script>alert('登陆失败');location.href='login.html'</script>");
        }
    }
}
