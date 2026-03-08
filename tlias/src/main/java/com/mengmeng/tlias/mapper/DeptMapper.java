package com.mengmeng.tlias.mapper;

import com.mengmeng.tlias.anno.Log;
import com.mengmeng.tlias.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {
    /**
     * 查询全部部门
     * @return
     */
    @Select("select * from dept")
    public List<Dept> getDepts();

    /**
     * 根据id查询部门
     * @return
     */
    @Select("select * from dept where id=#{id}")
    public Dept getDeptById(Integer id);


    /**
     * 根据id删除部门
     * @param id
     */
    @Delete("delete from dept where id=#{id}")
    public void deleteById(Integer id);

    /**
     * 添加部门
     * @param dept
     */
    @Insert("insert into dept(name, create_time, update_time) values (#{name},#{createTime},#{updateTime})")
    public void insert(Dept dept);

    /**
     * 修改部门
     * @param dept
     */
    @Update("update dept set name=#{name},update_time=#{updateTime} where id=#{id}")
    public void update(Dept dept);


}
