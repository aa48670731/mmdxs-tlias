package com.mengmeng.tlias.service;


import com.mengmeng.tlias.pojo.Dept;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
public interface DeptService {
    //返回部门的全部数据
    public abstract List<Dept> list();

    //根据id返回部门数据
    public abstract Dept getDeptById(Integer id);

    //根据id删除部门
    public abstract void delete(Integer id) throws Exception;

    //添加部门
    public abstract void add(Dept dept) throws Exception;

    //根据id修改部门
    public abstract void updateDeptById(Dept dept);
}
