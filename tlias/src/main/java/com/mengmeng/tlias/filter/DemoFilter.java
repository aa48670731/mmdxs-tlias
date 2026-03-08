package com.mengmeng.tlias.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

//@WebFilter(urlPatterns = "/*") //拦截所有请求
public class DemoFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init");
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Demo 拦截到了请求...放行前逻辑");
        //放行操作
        filterChain.doFilter(servletRequest, servletResponse);

        System.out.println("Demo 拦截到了请求...放行后逻辑");
    }

    @Override
    public void destroy() {
        System.out.println("destroy");
        Filter.super.destroy();
    }
}
