package com.mengmeng.tlias.mapper;

import com.mengmeng.tlias.anno.Log;
import com.mengmeng.tlias.pojo.Emp;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {
//    /**
//     * 查询员工表的总记录数
//     * @return
//     */
//    @Select("select count(*) from emp")
//    public Long count();
//
//    /**
//     * 分页查询员工数据
//     * @return
//     */
//    @Select("select * from emp limit #{start},#{pageSize}")
//    public List<Emp> selectAllEmp(Integer start,Integer pageSize);

    /**
     * 查询员工信息
     * @return
     */
//    @Select("select * from emp")
    public List<Emp> list(String name, Short gender, LocalDate begin, LocalDate end);

    /**
     * 批量删除员工
     * @param ids
     */
    public void delete(List<Integer> ids);

    /**
     * 添加员工
     * @param emp
     */
    @Select("insert into emp(username, name, gender, image, job, entrydate, dept_id, create_time, update_time)" +
            "values (#{username},#{name},#{gender},#{image},#{job},#{entrydate},#{deptId},#{createTime},#{updateTime})")
    public void insert(Emp emp);

    /**
     * 根据id查询员工
     * @param id
     * @return
     */
    @Select("select * from emp where id=#{id}")
    public Emp getEmpById(Integer id);

    /**
     * 修改员工
     * @param emp
     */
    public void update(Emp emp);

    /**
     * 根据用户名和密码查询员工
     * @param emp
     * @return
     */
    @Select("select * from emp where username=#{username} and password=#{password}")
    public Emp getByUsernameAndPassword(Emp emp);

    /**
     * 根据部门ID删除该部门下的员工数据
     * @param deptId
     */
    @Delete("delete from emp where dept_id=#{deptId}")
    public void deleteByDeptId(Integer deptId);

}
