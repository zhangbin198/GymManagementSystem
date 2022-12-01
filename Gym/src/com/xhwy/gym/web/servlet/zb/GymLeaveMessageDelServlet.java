package com.xhwy.gym.web.servlet.zb;

import com.xhwy.gym.service.zb.LeaveMessageService;
import com.xhwy.gym.service.zb.impl.LeaveMessageServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 根据编号删除指定入账信息控制层
 *
 * @author 张斌
 * @version 1.0
 * @date 2022/7/20 14:42
 */
@WebServlet("/GymLeaveMessageDelServlet.do")
public class GymLeaveMessageDelServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置请求编码
        req.setCharacterEncoding("UTF-8");
        //设置响应编码
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        //获取删除id参数
        String leavemessageId = req.getParameter("leavemessageId");
        //实例化业务层
        LeaveMessageService leaveMessages = new LeaveMessageServiceImpl();
        int i = leaveMessages.delLeaveMessage(Integer.parseInt(leavemessageId));
        //判断是否删除成功
        if (i == 1) {
            resp.getWriter().write("success");
        } else {
            resp.getWriter().write("fail");
        }
    }
}
