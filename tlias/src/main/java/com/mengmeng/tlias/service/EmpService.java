package com.mengmeng.tlias.service;

import com.mengmeng.tlias.pojo.Emp;
import com.mengmeng.tlias.pojo.PageBean;
import java.time.LocalDate;
import java.util.List;

public interface EmpService {

    //根据ID查询员工
    public Emp getEmpById(Integer id);

    //分页查询员工数据
    public PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end);

    //批量删除员工
    public void delete(List<Integer> ids);

    //添加员工
    public void save(Emp emp);

    //修改员工
    public void update(Emp emp);

    //员工登录
    public Emp login(Emp emp);
}
