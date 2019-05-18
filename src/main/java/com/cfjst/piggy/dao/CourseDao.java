package com.cfjst.piggy.dao;

import java.util.List;

import com.cfjst.piggy.bean.Clazz;
import com.cfjst.piggy.bean.Course;
import com.cfjst.piggy.bean.Student;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.FetchType;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Many;


public interface CourseDao {

	/**
	 * 通过班级Id获取课程信息
	 * @param id 班级Id
	 * @return 课程信息
	 */

	@Select("SELECT course.id,course.name FROM sbff.course_clazz ,sbff.clazz ,sbff.course WHERE course_clazz.id_clazz=clazz.id AND course_clazz.id_course=course.id AND clazz.id=#{id}")
	@Results({
        @Result(property = "id", column = "course.id"),
        @Result(property = "name", column = "course.name"),
        @Result(property = "clazzId", column = "id_clazz")
    })
	public List<Course> findByClazzId(Integer id);


	
	/**
	 * 通过学生Id获取课程信息
	 * @param id 班级Id
	 * @return 课程信息
	 */
	@Select("SELECT course.id,course.name,clazz.id as id_class FROM sbff.course_clazz ,sbff.clazz ,sbff.course,sbff.student where course_clazz.id_clazz=clazz.id AND course_clazz.id_course=course.id AND clazz.id=student.id_clazz AND student.id=#{id}")
	@Results({
        @Result(property = "id", column = "course.id"),
        @Result(property = "name", column = "course.name"),
        @Result(property = "clazzId", column = "id_clazz")
    })
	public List<Course> findByStudentId(Integer id);












}
