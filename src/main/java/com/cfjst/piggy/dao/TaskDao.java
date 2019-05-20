package com.cfjst.piggy.dao;

import java.util.List;

import com.cfjst.piggy.bean.Clazz;
import com.cfjst.piggy.bean.Course;
import com.cfjst.piggy.bean.Task;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;


public interface TaskDao {


    
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
     * 获取班级的信息，不包括课程信息
     * @param id 班级ID
     * @return 班级信息
     */
    @Select("select * from clazz where id = #{id}")
	public Clazz findByClazzId(Integer id);

    @Select("SELECT small_task.name AS sn ,big_task.name AS bn ,small_task.deadline,score.score FROM sbff.small_task,sbff.big_task,sbff.score where score.id_course=#{courseId} and score.id_student=#{student_id} and score.id_small_task=small_task.id and score.id_big_task=big_task.id")
    @Results({
        @Result(property = "smallTaskName",column = "sn"),
        @Result(property = "bigTaskName",column = "bn"),
        @Result(property = "deadline",column = "deadline"),
        @Result(property = "score",column = "score")
    })
    public List<Task> findSmallTaskByCASId(Integer courseId,Long student_id);

	// private String smallTaskName;
	// private String bigTaskName;
	// private Date deadline;
	// private String status;
	// private Integer score;
}