package com.mengmeng.tlias;

import com.mengmeng.tlias.controller.DeptController;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.UUID;

@SpringBootTest
class TliasApplicationTests {

    @Test
    void contextLoads() {
        for (int i = 0; i < 10; i++) {
            String uuid = UUID.randomUUID().toString();
            System.out.println(uuid);
        }
    }

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void testGetBean() {
        //根据bean的名称获取
        DeptController bean1 = (DeptController) applicationContext.getBean("deptController");
        System.out.println(bean1);
        //根据bean的类型获取
        DeptController bean2 = applicationContext.getBean(DeptController.class);
        System.out.println(bean2);
        //根据bean的名称以及类型获取
        DeptController bean3 = applicationContext.getBean("deptController", DeptController.class);
        System.out.println(bean3);
    }

    @Test
    public void testScope() {
        for (int i = 0; i < 10; i++) {
            DeptController deptController = (DeptController) applicationContext.getBean(DeptController.class);
            System.out.println(deptController);
        }
    }

    @Autowired
    private SAXReader saxReader;
    @Test
    public void testThirdBean() throws DocumentException {
//        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(this.getClass().getClassLoader().getResource("1.xml"));
        Element rootElement = document.getRootElement();
        String name=rootElement.element("name").getText();
        String age=rootElement.element("age").getText();
        System.out.println(name+":"+age);
    }

    @Test
    public void testGetBean2() {
        //根据bean的名称获取
        SAXReader bean1 = (SAXReader) applicationContext.getBean("saxReader");
        System.out.println(bean1);

    }
}
