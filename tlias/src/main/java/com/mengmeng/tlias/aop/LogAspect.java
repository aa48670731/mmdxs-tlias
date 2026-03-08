package com.mengmeng.tlias.aop;

import com.alibaba.fastjson2.JSON;
import com.mengmeng.tlias.mapper.OperateLogMapper;
import com.mengmeng.tlias.pojo.OperateLog;
import com.mengmeng.tlias.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;


@Slf4j
@Component
@Aspect
public class LogAspect {
    //自动注入请求request对象，从请求中获取请求头里的jwt令牌
    @Autowired
    private HttpServletRequest request;

    //记录日志的mapper接口
    @Autowired
    private OperateLogMapper operateLogMapper;

    @Around("@annotation(com.mengmeng.tlias.anno.Log)")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {
        //操作人ID - 当前登录员工的ID
        //获取请求头中的jwt令牌，解析令牌就可以拿到当前登录员工的ID了
        String jwt = request.getHeader("token");    //拿到jwt令牌
        Claims claims = JwtUtils.parseJWT(jwt);
        Integer operateUser = (Integer) claims.get("id");

        //操作时间
        LocalDateTime operateTime = LocalDateTime.now();
        //操作类名
        String className = joinPoint.getTarget().getClass().getName();
        //操作方法名
        String methodName = joinPoint.getSignature().getName();
        //操作方法参数
        Object[] args = joinPoint.getArgs();
        String methodParams= Arrays.toString(args);
        //调用原始的目标方法运行
        long begin=System.currentTimeMillis();
        Object res = joinPoint.proceed();
        long end=System.currentTimeMillis();
        //方法返回值，转成json字符串存起来
        String returnValue = JSON.toJSONString(res);
        //操作耗时
        Long costTime=end-begin;

        OperateLog operateLog = new OperateLog(null,operateUser,operateTime,className,methodName,methodParams,returnValue,costTime);
        operateLogMapper.insert(operateLog);
        log.info("AOP记录操作日志：{}",operateLog);
        return res;
    }

}
