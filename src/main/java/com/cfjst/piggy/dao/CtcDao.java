package com.cfjst.piggy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.FetchType;
import org.apache.ibatis.annotations.One;

import com.cfjst.piggy.bean.CTC;
import com.cfjst.piggy.bean.Clazz;
import com.cfjst.piggy.bean.Course;
import com.cfjst.piggy.bean.Student;

public interface CtcDao {
	/**
	 * find
	 * @return
	 */
	@Select("select * from course_clazz")
    @Results({
    	//一对一，one=@one 对吗。。。对- -
    	@Result(property="id",column="id", id=true),
    	@Result(property="course",column="id_course",
			one = @One(select="com.cfjst.piggy.dao.CourseDao.findByCourseId",fetchType=FetchType.EAGER)),
    	@Result(property="clazz",column="id_clazz",
    		one = @One(select="com.cfjst.piggy.dao.ClazzDao.findByClazzId",fetchType=FetchType.EAGER)),
    	@Result(property="teacher",column="id_teacher",
    	    one = @One(select="com.cfjst.piggy.dao.TeacherDao.findByTeacherId",fetchType=FetchType.EAGER))
    })
    public List<CTC> findAllCourse();
    
	@Select("select * from course_clazz where id=#{id}")
	@Results({
		//一对一，one=@one 对吗。。。对- -
		@Result(property="id",column="id", id=true),
		@Result(property="course",column="id_course",
			one = @One(select="com.cfjst.piggy.dao.CourseDao.findByCourseId",fetchType=FetchType.EAGER)),
		@Result(property="clazz",column="id_clazz",
			one = @One(select="com.cfjst.piggy.dao.ClazzDao.findByClazzId",fetchType=FetchType.EAGER)),
		@Result(property="teacher",column="id_teacher",
			one = @One(select="com.cfjst.piggy.dao.findByTeacherId",fetchType=FetchType.EAGER))
	})
    	
    public CTC findByCTCId(int id);
    
    @Insert("insert into course_clazz(id_course, id_clazz, id_teacher) values (#{course.id}, #{clazz.id}, #{teacher.id})")
    public void addCTC(CTC ctc);
    @Insert("insert into course(id, name) value (#{id}, #{name})")
    public void addCourse(Course course);
    
    
    @Update({ "update course_clazz set id_clazz = #{clazz.id},id_teacher = #{teacher.id} where id = #{id}" })
    public void updateCTC(CTC ctc);
    @Update({ "update course set name = #{name} where id = #{id}" })
    public void updateCourse(Course course);
    
    @Delete("delete from course_clazz where id = #{id}")
    public void deleteCourse(int id);
    
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