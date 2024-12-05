package com.wolvescoding.nownovel.core.filter;

import com.wolvescoding.nownovel.core.auth.UserHolder;
import com.wolvescoding.nownovel.core.wrapper.xssHttpServletRequestWrapper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

public class XssFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        Long userId = UserHolder.getUserId();
        if (userId == null) {
            // 你可以在这里记录日志，或者设置一个默认值
            System.out.println("userId is null");
        } else {
            System.out.println("Current userId is: " + userId);
        }
        xssHttpServletRequestWrapper xssHttpServletRequestWrapper = new xssHttpServletRequestWrapper((HttpServletRequest) req);
       chain.doFilter(xssHttpServletRequestWrapper, resp);// 执行过滤链
    }
}
