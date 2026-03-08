package com.mengmeng.tlias.filter;

import com.alibaba.fastjson2.JSON;
import com.mengmeng.tlias.pojo.Result;
import com.mengmeng.tlias.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;


@Slf4j
//@WebFilter(urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        //1.获取请求url
        String url=req.getRequestURL().toString();
        log.info("请求的url: {}",url);

        //2.判断请求url中是否包含login，如果包含，证明是登录操作，放行
        if(url.contains("login")){
            log.info("登录操作，放行...");
            filterChain.doFilter(servletRequest,servletResponse);
            return; //此时无需执行下面的步骤
        }

        //3.获取请求头中的令牌(token)
        String jwt=req.getHeader("token");
        //4.判断令牌是否存在，若不存在，返回错误结果（未登录）
        if(!StringUtils.hasLength(jwt)){
            log.info("请求头token为空，返回未登录的信息");
            Result error=Result.error("NOT_LOGIN");
            //手动转换对象--json数据
            String notLogin = JSON.toJSONString(error);
            //手动进行数据响应
            resp.getWriter().write(notLogin);
            return;
        }
        //5.解析token，如果解析失败，返回错误结果（未登录）
        try {
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("解析令牌失败，返回未登录错误信息");
            Result error=Result.error("NOT_LOGIN");
            //手动转换对象--json数据
            String notLogin = JSON.toJSONString(error);
            //手动进行数据响应
            resp.getWriter().write(notLogin);
            return;
        }

        //6.放行
        log.info("令牌合法，放行");
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
