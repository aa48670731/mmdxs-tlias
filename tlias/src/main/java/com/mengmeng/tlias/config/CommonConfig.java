package com.mengmeng.tlias.config;

import com.mengmeng.tlias.service.DeptService;
import org.dom4j.io.SAXReader;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
public class CommonConfig {

    //声明第三方bean
    @Bean   //将该方法的返回值对象交给IOC容器管理
            // 通过@Bean注解的name/value属性指定bean名称，如果未指定，第三方bean对象名称就是该方法名
    public SAXReader saxReader(){
        return new SAXReader();
    }
}
