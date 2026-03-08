package com.mengmeng.tlias;

import com.example.MyImportSelector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Import;


@ServletComponentScan //开启了对servlet组件的支持
//@Import({TokenParser.class})    //导入普通类，交给ioc容器管理
//@Import(HeaderConfig.class)     //导入配置类，将里面的所有bean对象交给ioc容器管理
//@EnableHeaderConfig  //使用itheimaUtils依赖包的时候用的
@Import({MyImportSelector.class})     //导入importselector的实现类
@SpringBootApplication
public class TliasApplication {

    public static void main(String[] args) {
        SpringApplication.run(TliasApplication.class, args);

    }
}
