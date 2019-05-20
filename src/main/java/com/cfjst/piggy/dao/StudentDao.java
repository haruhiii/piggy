package com.cfjst.piggy.dao;

import com.cfjst.piggy.bean.Student;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * 此处需注释
 */
@Mapper
public interface StudentDao {

    /**
     * 按ID查找用户
     * @param id 学号或工号
     */
    @Select("select * from `sbff`.student where id=#{id}")
    public Student findById(Long id);
    
    /**
     * 添加用户
     */
    @Insert("INSERT INTO `sbff`.`student` (`id`, `password`, `name`,`id_clazz`) VALUES (#{id}, #{password}, #{name}, #{clazzId})")
    public void add(Student student);

    /**
     * 删除用户
     * @param id 学号或工号
     */
    @Delete("DELETE FROM `sbff`.`student` WHERE (`id` = #{id})")
    public void delById(Long id);

    /**
     * 修改用户信息
     */
    @Update({ "update student set name = #{name},password = #{password} where id = #{id}" })
    public void update(Student student);
    
   
    
}