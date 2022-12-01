package com.xhwy.gym.web.servlet.zb;

import com.xhwy.gym.entity.Booked;
import com.xhwy.gym.service.zb.BookedService;
import com.xhwy.gym.service.zb.impl.BookedServiceImpl;

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
@WebServlet("/GymBookedEditServlets.do")
public class GymBookedEditServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置请求编码
        req.setCharacterEncoding("UTF-8");
        //设置响应编码
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        //获取参数
        String billId = req.getParameter("billId");
        String billingAccount = req.getParameter("billingAccount");
        String billName = req.getParameter("billName");
        String whetherMembers = req.getParameter("whetherMembers");
        String billMoney = req.getParameter("billMoney");
        String billNotel = req.getParameter("billNotel");
        String consumerPlace = req.getParameter("consumerPlace");
        //封装实体类
        Booked booked = new Booked();
        booked.setBillId(Integer.parseInt(billId));
        booked.setBillingAccount(Integer.parseInt(billingAccount));
        booked.setBillName(billName);
        booked.setWhetherMembers(whetherMembers);
        booked.setBillMoney(Double.parseDouble(billMoney));
        booked.setBillNotel(billNotel);
        booked.setConsumerPlace(consumerPlace);
        //实例化业务层
        BookedService bookeds = new BookedServiceImpl();
        int i = bookeds.editBooked(booked);
        //判断是否编辑成功
        if (i == 1) {
            resp.getWriter().write("success");
        } else {
            resp.getWriter().write("fail");
        }
    }
}
