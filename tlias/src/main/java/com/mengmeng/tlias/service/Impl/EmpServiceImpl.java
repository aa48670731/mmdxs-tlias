package com.mengmeng.tlias.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mengmeng.tlias.service.EmpService;
import com.mengmeng.tlias.mapper.EmpMapper;
import com.mengmeng.tlias.pojo.Emp;
import com.mengmeng.tlias.pojo.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

    @Override
    public Emp getEmpById(Integer id) {
        return empMapper.getEmpById(id);
    }

    //    @Override
//    public PageBean page(Integer page, Integer pageSize) {
//        int start = (page - 1) * pageSize;
//        return new PageBean(empMapper.count(),empMapper.selectAllEmp(start,pageSize));
//    }
    @Override
    public PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end) {
        //设置分页参数
        PageHelper.startPage(page,pageSize);
        //执行查询,实际上获得的是分页查询的封装结果，需要强转为Page<>
        List<Emp> list = empMapper.list(name, gender, begin, end);
        Page<Emp> p= (Page<Emp>) list;
        //封装PageBean对象
        PageBean pageBean = new PageBean(p.getTotal(),p.getResult());
        return pageBean;
    }

    @Override
    public void delete(List<Integer> ids) {
        empMapper.delete(ids);
    }

    @Override
    public void save(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);
    }

    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);
    }

    @Override
    public Emp login(Emp emp) {
        return empMapper.getByUsernameAndPassword(emp);
    }
}
