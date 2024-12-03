package com.wolvescoding.nownovel.core.filter;

import com.wolvescoding.nownovel.core.wrapper.xssHttpServletRequestWrapper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

public class XssFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        xssHttpServletRequestWrapper xssHttpServletRequestWrapper = new xssHttpServletRequestWrapper((HttpServletRequest) req);
       chain.doFilter(xssHttpServletRequestWrapper, resp);// 执行过滤链
    }
}
