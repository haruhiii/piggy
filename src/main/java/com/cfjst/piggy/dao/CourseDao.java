package com.cfjst.piggy.dao;

import java.util.List;

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


public interface CourseDao {



	@Select("select * from course where id_clazz = #{id}")
	@Results({
		@Result(property = "classId",column = "id_class")
	})
	public List<Course> findByClazzId(Integer id);
















	/**
	 * find
	 * @return
	 */

	@Select("select * from course")
    @Results({
    	//一对一，one=@one
    	@Result(property="CourseId",column="Course_id", id=true),
    	@Result(property="Name",column="Name"),
    	@Result(property="classes",column="Class_id",
    		one = @One(select="com.example.demo.dao.ClassesDao.findByClassId",fetchType=FetchType.EAGER)),
    	@Result(property="teachers",column="Teacher_id",
    	    one = @One(select="com.example.demo.dao.TeacherDao.findByTeacherId",fetchType=FetchType.EAGER))
    })
    public List<Course> findAll();
    
    @Select("select * from course where id = #{id}")
    @Results({
    	//一对一，one=@one
    	@Result(property="CourseId",column="Course_id", id=true),
    	@Result(property="Name",column="Name"),
    	@Result(property="grade",column="grade"),
    	@Result(property="classes",column="Class_id",
    		one = @One(select="com.example.demo.dao.ClassesDao.findByClassId",fetchType=FetchType.EAGER)),
    	@Result(property="teachers",column="Teacher_id",
    	    one = @One(select="com.example.demo.dao.TeacherDao.findByTeacherId",fetchType=FetchType.EAGER))
    })
    public Course findById(int CourseId);
	
	


    @Insert("insert into course values (#{CourseId}, #{Name}, #{classes.Class_id}, #{teachers.Teacher_id}, 90)")
    public void add(Course course);
    
    @Update({ "update course set Name = #{Name},Class_id = #{classes.Class_id},Teacher_id = #{teachers.Teacher_id} where Course_id = #{CourseId}" })
    public void update(Course course);
    
    @Delete("delete from course where Course_id = #{CourseId}")
    public void delete(int CourseId);
    
    @Select("select * from student")
    @Results({
    	//一对一，one=@one
    	@Result(property="Student_id",column="Student_id", id=true),
    	@Result(property="Name",column="Name"),
    	@Result(property="sex",column="sex"),
    	@Result(property="Password",column="Password"),
    	@Result(property="Course",column="Course")
    })
    public Student findStudent();
}
