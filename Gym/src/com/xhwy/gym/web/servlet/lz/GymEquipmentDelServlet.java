package com.xhwy.gym.web.servlet.lz;

import com.xhwy.gym.service.Lz.EquipmentService;
import com.xhwy.gym.service.Lz.Impl.EquipmentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 根据编号删除指定器材
 *
 * @author 廖振
 * @version 1.0
 * @date 2022/7/20 14:42
 */
@WebServlet("/GymEquipmentDelServlet.do")
public class GymEquipmentDelServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置请求编码
        req.setCharacterEncoding("UTF-8");
        //设置响应编码
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        //获取删除id参数
        String equipmentId = req.getParameter("equipmentId");
        //实例化业务层
        EquipmentService equipments = new EquipmentServiceImpl();
        int i = equipments.delEquipment(Integer.parseInt(equipmentId));
        //判断是否删除成功
        if (i == 1) {
            resp.getWriter().write("success");
        }
    }
}
