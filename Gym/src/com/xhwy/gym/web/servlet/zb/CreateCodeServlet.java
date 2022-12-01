package com.xhwy.gym.web.servlet.zb;

import com.xhwy.gym.util.SendSMS;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

/**
 * @author 张斌
 * 生成验证码发送短信
 * @version 1.0
 * @date 2022/7/15 0:08
 */
@WebServlet("/createCode.do")
public class CreateCodeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置请求编码
        req.setCharacterEncoding("UTF-8");
        //设置响应编码
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        //获取参数
        String mobile = req.getParameter("mobile");
        //生成验证码
        String code = createCode(4);
        System.out.println("验证码:" + code);
        //响应给用户
        PrintWriter out = resp.getWriter();
        HttpSession session = req.getSession();
        //发送短信
        if (SendSMS.sendSMS(mobile, code)) {
            //共享验证码
            session.setAttribute("code", code);
            out.write("true");
        } else {
            session.removeAttribute("code");
            out.write("false");
        }
        //释放资源
        out.flush();
        out.close();
    }

    /**
     * 生成验证码
     *
     * @param length 验证码长度
     * @return 验证码
     */
    public String createCode(int length) {
        //创建StringBuffer对象
        StringBuffer code = new StringBuffer("");
        //创建随机数对象
        Random random = new Random();
        //放进数据
        String[] codeArr = "0,1,2,3,4,5,6,7,8,9".split(",");
        //循环遍历验证码
        for (int i = 0; i < length; i++) {
            code.append(codeArr[random.nextInt(codeArr.length)]);
        }
        //返回String类型的验证码
        return code.toString();
    }
}