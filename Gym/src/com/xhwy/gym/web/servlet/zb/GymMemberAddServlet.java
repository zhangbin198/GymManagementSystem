package com.xhwy.gym.web.servlet.zb;

import com.xhwy.gym.entity.MemberAll;
import com.xhwy.gym.service.zb.MemberService;
import com.xhwy.gym.service.zb.impl.MemberServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 添加会员信息数据控制层
 *
 * @author 张斌
 * @version 1.0
 * @date 2022/7/20 11:32
 */
@WebServlet("/GymMemberAddServlet.do")
public class GymMemberAddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置请求编码
        req.setCharacterEncoding("UTF-8");
        //设置响应编码
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        //获取参数
        String memberName = req.getParameter("memberName");
        String memberGender = req.getParameter("memberGender");
        String memberAge = req.getParameter("memberAge");
        String memberHeight = req.getParameter("memberHeight");
        String memberWeight = req.getParameter("memberWeight");
        String memberPhone = req.getParameter("memberPhone");
        String cardClass = req.getParameter("cardClass");
        String cardNextClass = req.getParameter("cardNextClass");
        //封装实体类
        MemberAll member = new MemberAll();
        member.setMemberName(memberName);
        member.setMemberGender(memberGender);
        member.setMemberAge(Integer.parseInt(memberAge));
        member.setMemberHeight(Integer.parseInt(memberHeight));
        member.setMemberWeight(Integer.parseInt(memberWeight));
        member.setMemberPhone(Long.parseLong(memberPhone));
        member.setCardClass(Integer.parseInt(cardClass));
        member.setCardNextClass(Integer.parseInt(cardNextClass));
        //实例化业务层
        MemberService members = new MemberServiceImpl();
        int i = members.addMember(member);
        //判断是否添加成功
        if (i == 1) {
            resp.getWriter().write("success");
        } else {
            resp.getWriter().write("fail");
        }
    }
}
