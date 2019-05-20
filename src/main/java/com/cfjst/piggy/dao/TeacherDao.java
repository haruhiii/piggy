package com.cfjst.piggy.dao;

import com.cfjst.piggy.bean.Teacher;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;;

/**
 * 此处需注释
 */
@Mapper
public interface TeacherDao {

    /**
     * 按ID查找用户
     * @param id 学号或工号
     */
    @Select("select * from teacher where id=#{id}")
    public Teacher findById(Long id);
    
    /**
     * 添加用户
     */
      @Insert("INSERT INTO `sbff`.`teacher` (`id`, `password`, `name`) VALUES (#{id}, #{password}, #{name})")
    public void add(Teacher teacher);

    /**
     * 删除用户
     * @param id 学号或工号
     */
     @Delete("DELETE FROM `sbff`.`teacher` WHERE (`id` = #{id})")
    public void delById(Long id);

    /**
     * 修改用户信息
     */
     @Update({ "update teacher set name = #{name},password = #{password} where id = #{id}" })
    public void update(Teacher teacher);
    
   
    
}