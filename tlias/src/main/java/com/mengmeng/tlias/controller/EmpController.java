package com.mengmeng.tlias.controller;

import com.mengmeng.tlias.anno.Log;
import com.mengmeng.tlias.service.EmpService;
import com.mengmeng.tlias.pojo.Emp;
import com.mengmeng.tlias.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RequestMapping("/emps")
@RestController
public class EmpController {
    @Autowired
    EmpService empService;

    //分页查询员工
    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name, Short gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        log.info("分页查询：{},{},{},{},{},{},", page, pageSize, name, gender, begin, end);
        return Result.success(empService.page(page, pageSize, name, gender, begin, end));
    }

    //批量删除员工、删除单个员工统一接口
    @Log
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids) {
        log.info("批量删除操作，ids:{} ", ids);
        empService.delete(ids);
        return Result.success();
    }

    //添加员工
    @Log
    @PostMapping
    public Result save(@RequestBody Emp emp) {
        log.info("新增员工emp: {}", emp);
        empService.save(emp);
        return Result.success();
    }

    //根据ID查询员工
    @GetMapping("{id}")
    public Result getEmpById(@PathVariable Integer id) {
        log.info("根据id: {} 查询员工",id);
        Emp emp = empService.getEmpById(id);
        return Result.success(emp);
    }

    //修改员工数据
    @Log
    @PutMapping
    public Result update(@RequestBody Emp emp) {
        log.info("更新员工信息: {}",emp);
        empService.update(emp);
        return Result.success();
    }

}
