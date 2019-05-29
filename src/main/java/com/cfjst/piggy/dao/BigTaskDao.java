package com.cfjst.piggy.dao;

import java.util.List;

import com.cfjst.piggy.bean.BigTask;
import com.cfjst.piggy.bean.Clazz;
import com.cfjst.piggy.bean.Course;
import com.cfjst.piggy.bean.SmallTask;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

@Mapper
public interface BigTaskDao {


    
    @Select("select * from clazz where id = #{id}")
    public List<Course> findCourseByStudentId(Long id);

    // SELECT 
    //     score.score
    // FROM
    //     sbff.score
    // WHERE
    //     score.id_student=2016051094  AND
    //     score.id_course=1\
    
    
    @Select("SELECT score.score,score.id_big_task FROM sbff.score WHERE score.id_student=${studentId}  AND score.id_course=${courseId}")
    @Results({
        @Result(property = "score",column = "score"),
        @Result(property = "bigTaskId",column = "id_big_task")
    })
    public List<SmallTask> getSmallTaskScoreBySSId(Long studentId,Integer courseId);

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




    /**
         * SELECT 
                avg(score)
            FROM
                sbff.score
            WHERE
                score.id_student = 2016051094 and
                score.id_course = 1 and
                score.id_big_task = 2
     * 获取某一个大项的得分百分比
     */
    @Select("SELECT avg(score) FROM sbff.score WHERE score.id_student = ${studentId} and score.id_course = ${courseId} and score.id_big_task = ${bigTaskId} and score.score<>-1 and score.score<>-2")
    public Integer getBigTaskAvg(Integer courseId,Long studentId,Integer bigTaskId);

	// private String smallTaskName;
	// private String bigTaskName;
	// private Date deadline;
	// private String status;
	// private Integer score;
}