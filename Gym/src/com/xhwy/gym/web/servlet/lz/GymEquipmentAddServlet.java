package com.xhwy.gym.web.servlet.lz;

import com.xhwy.gym.entity.Equipment;
import com.xhwy.gym.service.Lz.EquipmentService;
import com.xhwy.gym.service.Lz.Impl.EquipmentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 添加器材信息
 *
 * @author 廖振
 * @version 1.0
 * @date 2022/7/20 11:32
 */
@WebServlet("/GymEquipmentAddServlet.do")
public class GymEquipmentAddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置请求编码

        //设置响应编码

        resp.setContentType("text/html;charset=utf-8");
        //获取参数
        String equipmentName = req.getParameter("equipmentName");
        String equipmentLocation = req.getParameter("equipmentLocation");
        String equipmentStatus = req.getParameter("equipmentStatus");
        String equipmentMessage = req.getParameter("equipmentMessage");
        //封装实体类
        Equipment equipment = new Equipment();
        equipment.setEquipmentName(equipmentName);
        equipment.setEquipmentLocation(equipmentLocation);
        equipment.setEquipmentStatus(equipmentStatus);
        equipment.setEquipmentMessage(equipmentMessage);
        //实例化业务层
        EquipmentService equipments = new EquipmentServiceImpl();
        int i = equipments.addEquipment(equipment);
        //判断是否添加成功
        if (i == 1) {
            resp.getWriter().write("success");
        } else {
            resp.getWriter().write("fail");
        }
    }
}
