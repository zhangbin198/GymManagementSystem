package com.xhwy.gym.web.servlet.skc;

import com.alibaba.fastjson.JSONObject;
import com.xhwy.gym.entity.Vo;
import com.xhwy.gym.service.skc.ClassOderService;
import com.xhwy.gym.service.skc.impl.ClassOderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 查询所有报名信息数据控制层
 *
 * @author 佘柯澂
 * @version 1.0
 * @date 2022/7/19 8:29
 */
@WebServlet("/GymClassOrderSelectServlet.do")
public class GymClassOrderSelectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置请求编码
        req.setCharacterEncoding("UTF-8");
        //设置响应编码
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        //获取分页参数
        String pageStr = req.getParameter("page");
        String limitStr = req.getParameter("limit");
        //实例化业务层
        ClassOderService classOrders = new ClassOderServiceImpl();
        List<Object> list = classOrders.allEquipment(pageStr, limitStr);//查询所有会员信息并分页
        //封装与ajax打交道的实体类
        Vo vo = new Vo();
        vo.setCode(0);//解析接口状态
        vo.setMsg("success");//解析提示文本
        vo.setCount(classOrders.countClassOrder());//解析数据长度
        vo.setData(list);//解析数据列表
        resp.getWriter().write(JSONObject.toJSONString(vo));//list集合转json
    }
}
