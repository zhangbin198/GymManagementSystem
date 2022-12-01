package com.xhwy.gym.web.servlet.zb;

import com.xhwy.gym.entity.Leavemessage;
import com.xhwy.gym.service.zb.LeaveMessageService;
import com.xhwy.gym.service.zb.impl.LeaveMessageServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 编辑入账信息数据控制层
 *
 * @author 张斌
 * @version 1.0
 * @date 2022/7/20 6:26
 */
@WebServlet("/GymLeaveMessageEditServlets.do")
public class GymLeaveMessageEditServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置请求编码
        req.setCharacterEncoding("UTF-8");
        //设置响应编码
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        //获取参数
        String leavemessageId = req.getParameter("leavemessageId");
        String leavemessageUser = req.getParameter("leavemessageUser");
        String leavemessageContent = req.getParameter("leavemessageContent");
        String leavemessagePhone = req.getParameter("leavemessagePhone");
        //封装实体类
        Leavemessage leaveMessage = new Leavemessage();
        leaveMessage.setLeavemessageId(Integer.parseInt(leavemessageId));
        leaveMessage.setLeavemessageUser(leavemessageUser);
        leaveMessage.setLeavemessageContent(leavemessageContent);
        leaveMessage.setLeavemessagePhone(leavemessagePhone);
        //实例化业务层
        LeaveMessageService leaveMessages = new LeaveMessageServiceImpl();
        int i = leaveMessages.editLeaveMessage(leaveMessage);
        //判断是否修改成功
        if (i == 1) {
            resp.getWriter().write("success");
        } else {
            resp.getWriter().write("fail");
        }
    }
}
