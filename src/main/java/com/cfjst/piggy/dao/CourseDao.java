package com.cfjst.piggy.dao;

import java.util.List;

import com.cfjst.piggy.bean.Course;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;


public interface CourseDao {

	/**
	 * 通过班级Id获取课程信息
	 * @param id 班级Id
	 * @return 课程信息
	 */

	@Select("SELECT course.id,course.name, clazz.id AS id_clazz FROM sbff.course_clazz ,sbff.clazz ,sbff.course WHERE course_clazz.id_clazz=clazz.id AND course_clazz.id_course=course.id AND clazz.id=#{id}")
	@Results({
        @Result(property = "id", column = "id"),
        @Result(property = "name", column = "name"),
        @Result(property = "clazzId", column = "id_clazz")
    })
	public List<Course> findByClazzId(Integer id);


	
	/**
	 * 通过学生Id获取课程信息
	 * @param id 学号
	 * @return 课程信息
	 */
	@Select("SELECT course.id,course.name, clazz.id AS id_clazz FROM sbff.course_clazz ,sbff.clazz ,sbff.course,sbff.student where course_clazz.id_clazz=clazz.id AND course_clazz.id_course=course.id AND clazz.id=student.id_clazz AND student.id=#{id}")
	@Results({
        @Result(property = "id", column = "id"),
        @Result(property = "name", column = "name"),
        @Result(property = "clazzId", column = "id_clazz")
    })
	public List<Course> findByStudentId(Long id);












}
