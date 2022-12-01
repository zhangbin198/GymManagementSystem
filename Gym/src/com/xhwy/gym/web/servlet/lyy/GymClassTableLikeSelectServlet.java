package com.xhwy.gym.web.servlet.lyy;

import com.alibaba.fastjson.JSONObject;
import com.xhwy.gym.entity.Vo;
import com.xhwy.gym.service.lyy.ClassTableService;
import com.xhwy.gym.service.lyy.impl.ClassTableServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 模糊查询器材信息
 *
 * @author 李缘缘
 * @version 1.0
 * @date 2022/7/21 23:32
 */
@WebServlet("/GymClassTableLikeSelectServlet.do")
public class GymClassTableLikeSelectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置请求编码
        req.setCharacterEncoding("utf-8");
        //设置响应编码
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        //获取分页参数
        String pageStr = req.getParameter("page");
        String limitStr = req.getParameter("limit");
        //获取模糊查询参数
        String classIdStr = req.getParameter("classId");
        String classNameStr = req.getParameter("className");
        String classBeginStr = req.getParameter("classBegin");
        String coachStr = req.getParameter("coach");
        //实例化业务层
        ClassTableService classTableService = new ClassTableServiceImpl();
        List<Object> list = classTableService.allClassTableLike(pageStr, limitStr, classIdStr, classNameStr, classBeginStr, coachStr);
        //封装与ajax打交道实体类
        Vo vo = new Vo();
        vo.setCode(0);//解析接口状态
        vo.setMsg("success");//解析提示文本
        vo.setCount(classTableService.countClassTable());//解析数据长度
        vo.setData(list);//解析数据列表
        resp.getWriter().write(JSONObject.toJSONString(vo));//list集合转json
    }
}
