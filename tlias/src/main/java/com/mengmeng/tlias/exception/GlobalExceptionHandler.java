package com.mengmeng.tlias.exception;

import com.mengmeng.tlias.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class) //写Exception.class是捕获所有异常
    public Result ex(Exception ex) {
        ex.printStackTrace();
        String message = ex.getMessage();
        return Result.error("对不起，操作失败，请联系管理员，"+"错误信息:"+message);
    }
}
