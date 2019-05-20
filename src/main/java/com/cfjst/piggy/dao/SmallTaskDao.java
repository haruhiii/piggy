package com.cfjst.piggy.dao;

import java.util.List;

import com.cfjst.piggy.bean.BigTask;
import com.cfjst.piggy.bean.Clazz;
import com.cfjst.piggy.bean.Course;
import com.cfjst.piggy.bean.SmallTask;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;


public interface SmallTaskDao {


    
    @Select("select * from clazz where id = #{id}")
    public List<Course> findCourseByStudentId(Long id);



    /**
     * 获取班级的信息，包括课程信息
     * @param id 班级ID
     * @return 班级信息
     */
    @Select("select * from clazz where id = #{id}")
    @Results({

        @Result(property = "courses",column = "id",javaType = List.class,
            many =  @Many(select = "com.cfjst.piggy.dao.CourseDao.findByClazzId",fetchType = FetchType.LAZY))
    })
    public Clazz findByClazzIdWithCourse(Integer id);
    
   /**
    * 
    SELECT 
        small_task.name AS sn,
        big_task.name AS bn,
        small_task.deadline,
        score.score
    FROM
        sbff.small_task,
        sbff.big_task,
        sbff.score
    WHERE
        score.id_course = 1
        AND score.id_student = 2016051094
        AND score.id_small_task = small_task.id
        AND score.id_big_task = big_task.id
        AND big_task.id = small_task.big_task_id
    * 获取各个小项的信息
    * @param courseId
    * @param student_id
    * @return
    */
    @Select("SELECT small_task.name AS sn,big_task.name AS bn,small_task.deadline,score.score FROM sbff.small_task,sbff.big_task,sbff.score WHERE score.id_course = #{courseId} AND score.id_student = #{studentId} AND score.id_small_task = small_task.id AND score.id_big_task = big_task.id AND big_task.id = small_task.big_task_id")
    @Results({
        @Result(property = "smallTaskName",column = "sn"),
        @Result(property = "bigTaskName",column = "bn"),
        @Result(property = "deadline",column = "deadline"),
        @Result(property = "score",column = "score")
    })
    public List<SmallTask> findSmallTaskByCASId(Integer courseId,Long studentId);

    /**
     * 
    SELECT 
        big_task.id,
        big_task.name,
        big_task.rate
    FROM
        sbff.big_task,
        sbff.clazz,
        sbff.student
    WHERE
        clazz.id = student.id_clazz
        AND student.id = 2016051094
        AND big_task.id_course = 1
    * 获取各个大项的信息
    * @param courseId
    * @param student_id
    * @return
    */
    @Select("    SELECT  big_task.id,big_task.name,big_task.rate FROM sbff.big_task,sbff.clazz,sbff.student WHERE clazz.id = student.id_clazz AND student.id = ${studentId} AND big_task.id_course = ${courseId}     ")
    public List<BigTask> findBigTaskByCASId(Integer courseId,Long studentId);


	// private String smallTaskName;
	// private String bigTaskName;
	// private Date deadline;
	// private String status;
	// private Integer score;
}