package com.cfjst.piggy.dao;

import com.cfjst.piggy.bean.Student;
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
public interface UserDao{

    /**
     * 按ID查找用户
     * @param id 学号或工号
     */
    @Select("select * from student where id=#{id}")
    public Student findStudentById(Integer id);
    @Select("select * from teacher where id=#{id}")
    public Teacher findTeacherById(Integer id);
    
    /**
     * 添加用户
     */
    @Insert("INSERT INTO `sbff`.`student` (`id`, `password`, `name`) VALUES (#{id}, #{password}, #{name})")
    public void addStudent(Student student);
    @Insert("INSERT INTO `sbff`.`teacher` (`id`, `password`, `name`) VALUES (#{id}, #{password}, #{name})")
    public void addTeacher(Teacher teacher);

    /**
     * 删除用户
     * @param id 学号或工号
     */
    @Delete("DELETE FROM `sbff`.`student` WHERE (`id` = #{id})")
    public void delStudentById(Integer id);
    @Delete("DELETE FROM `sbff`.`teacher` WHERE (`id` = #{id})")
    public void delTeacherById(Integer id);

    /**
     * 修改用户信息
     */
    @Update({ "update student set name = #{name},password = #{password} where id = #{id}" })
    public void updateStudent(Student student);
    @Update({ "update teacher set name = #{name},password = #{password} where id = #{id}" })
    public void updateTeacher(Teacher teacher);
    
   
    
}