package com.mengmeng.tlias.controller;

import com.mengmeng.tlias.anno.Log;
import com.mengmeng.tlias.service.DeptService;
import com.mengmeng.tlias.pojo.Dept;
import com.mengmeng.tlias.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Lazy
//@Scope("singleton")
@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {
    @Autowired
    private DeptService deptService;

    public DeptController() {
        System.out.println("DeptController constructor");
    }

    //返回全部部门数据
//    @RequestMapping(value = "/depts",method = RequestMethod.GET)
    @GetMapping
    public Result list(){
        log.info("查询所有部门信息");
        List<Dept> list = deptService.list();
        return Result.success(list);
    }

    //根据id查询部门
    @GetMapping("/{id}")
    public Result getDeptById(@PathVariable Integer id){
        log.info("根据ID： "+id+" 查询部门");
        return Result.success(deptService.getDeptById(id));
    }

    //根据id删除某个部门
    @Log
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) throws Exception {
        log.info("根据id: "+id+" 来删除部门");
        deptService.delete(id);
        return Result.success();
    }

    //添加部门
    @Log
    @PostMapping
    public Result add(@RequestBody Dept dept) throws Exception {
        log.info("新增部门{}",dept);
        deptService.add(dept);
        return Result.success();
    }

    //根据id修改部门
    @Log
    @PutMapping
    public Result updateDeptById(@RequestBody Dept dept){
        log.info("根据id："+dept.getId()+"修改部门");
        deptService.updateDeptById(dept);
        return Result.success();
    }

}
