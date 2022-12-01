package com.xhwy.gym.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 字符过滤器
 *
 * @author 张斌
 * @version 1.0
 * @date 2022/7/7 16:53
 */
@WebFilter(value = "*.do")//只过滤后缀为.do的请求
public class CharacterFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("UTF-8");
        servletResponse.setCharacterEncoding("UTF-8");
        servletResponse.setContentType("text/html;charset=utf-8");
        //继续放行，执行下一个过滤器
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
