package com.cfjst.piggy.dao;

import java.util.List;

import com.cfjst.piggy.bean.Student;
import com.cfjst.piggy.bean.Teacher;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;;

/**
 * 此处需注释
 */
public interface StudentDao {

    /**
     * 按ID查找用户
     * @param id 学号或工号
     */
    @Select("select * from student where id=#{id}")
    public Student findById(Integer id);
    
    /**
     * 添加用户
     */
    @Insert("INSERT INTO `sbff`.`student` (`id`, `password`, `name`) VALUES (#{id}, #{password}, #{name})")
    public void add(Student student);

    /**
     * 删除用户
     * @param id 学号或工号
     */
    @Delete("DELETE FROM `sbff`.`student` WHERE (`id` = #{id})")
    public void delById(Integer id);

    /**
     * 修改用户信息
     */
    @Update({ "update student set name = #{name},password = #{password} where id = #{id}" })
    public void update(Student student);
    
   
    
}