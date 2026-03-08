package com.mengmeng.tlias.service.Impl;

import com.mengmeng.tlias.mapper.EmpMapper;
import com.mengmeng.tlias.pojo.DeptLog;
import com.mengmeng.tlias.pojo.Result;
import com.mengmeng.tlias.service.DeptLogService;
import com.mengmeng.tlias.service.DeptService;
import com.mengmeng.tlias.mapper.DeptMapper;
import com.mengmeng.tlias.pojo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    //mapper接口
    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private DeptLogService deptLogService;

    //返回部门的全部数据
    @Override
    public List<Dept> list() {
        List<Dept> list = deptMapper.getDepts();
        return list;
    }

    @Override
    public Dept getDeptById(Integer id) {
        return deptMapper.getDeptById(id);
    }

//    @Transactional(rollbackFor = Exception.class)  //spring事务管理
    @Transactional
    //根据id删除部门
    @Override
    public void delete(Integer id) throws Exception {
        try {
            deptMapper.deleteById(id);
            //模拟异常
//            int i=1/0;
//        if(true){
//            throw new Exception("出错啦...");
//        }
            //删除该部门下的全部员工
            empMapper.deleteByDeptId(id);
        } finally {
            DeptLog deptLog=new DeptLog();
            deptLog.setCreateTime(LocalDateTime.now());
            deptLog.setDescription("执行了解散部门的操作，此次解散的是"+id+"号部门");
            deptLogService.insert(deptLog);
        }
    }

    //添加部门
    @Override
    public void add(Dept dept) throws Exception {
        if(dept.getName()==null){
            throw new Exception("部门名字不能为空");
        }
        //记得补全其他必须的属性！
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.insert(dept);
    }

    @Override
    public void updateDeptById(Dept dept) {
        //补全更新时间属性
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.update(dept);
    }


}
