package com.cfjst.piggy.dao;

import java.util.List;

import com.cfjst.piggy.bean.BigTask;
import com.cfjst.piggy.bean.Clazz;
import com.cfjst.piggy.bean.Course;
import com.cfjst.piggy.bean.SmallTask;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.FetchType;


@Mapper
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
    // @Select("SELECT small_task.name AS sn,big_task.name AS bn,small_task.deadline,score.score FROM sbff.small_task,sbff.big_task,sbff.score WHERE score.id_course = #{courseId} AND score.id_student = #{studentId} AND score.id_small_task = small_task.id AND score.id_big_task = big_task.id AND big_task.id = small_task.big_task_id")
    // @Results({
    //     @Result(property = "smallTaskName",column = "sn"),
    //     @Result(property = "bigTaskName",column = "bn"),
    //     @Result(property = "deadline",column = "deadline"),
    //     @Result(property = "score",column = "score")
    // })
    // public List<SmallTask> findSmallTaskByCASId(Integer courseId,Long studentId);

       /**
    * 通过学生id和课程id查找学生的所有作业
        SELECT 
        big_task.id AS bi,
        small_task.name AS sn,
        big_task.name AS bn,
        small_task.deadline,
        big_task.id_course ,
        small_task.id
    FROM
        sbff.clazz,
        sbff.student,
        sbff.course_clazz,
        sbff.course,
        sbff.big_task,
        sbff.small_task
    WHERE
    	 student.id = 2016051094
        And course.id = 1
        AND clazz.id = student.id_clazz 
    	AND course_clazz.id_clazz = clazz.id
        And course_clazz.id_course = course.id 
        And big_task.id_course = course.id 
        And small_task.big_task_id = big_task.id
        ORDER BY deadline desc
        LIMIT 0,4;
        */ 
        @Select( " SELECT big_task.id AS bi, small_task.name AS sn, big_task.name AS bn, small_task.deadline, big_task.id_course , small_task.id FROM sbff.clazz,  sbff.student,sbff.course_clazz,sbff.course, sbff.big_task, sbff.small_task WHERE clazz.id = student.id_clazz  AND course_clazz.id_clazz = clazz.id And course_clazz.id_course = course.id  And big_task.id_course = course.id  And small_task.big_task_id = big_task.id AND student.id = #{studentId} And course.id = #{courseId} ORDER BY deadline desc")
        @Results({
            @Result(property = "smallTaskName",column = "sn"),
            @Result(property = "bigTaskName",column = "bn"),
            @Result(property = "deadline",column = "deadline"),
            @Result(property = "courseId",column = "id_course"),
            @Result(property = "bigTaskId",column = "bi"),
            @Result(property = "id",column = "id"),
        })
        public List<SmallTask> findSmallTaskByCASId(Integer courseId,Long studentId);

    
   /**
    * 通过学生id和课程id查找学生的所有作业分页
        SELECT 
        big_task.id AS bi,
        small_task.name AS sn,
        big_task.name AS bn,
        small_task.deadline,
        big_task.id_course ,
        small_task.id
    FROM
        sbff.clazz,
        sbff.student,
        sbff.course_clazz,
        sbff.course,
        sbff.big_task,
        sbff.small_task
    WHERE
    	 student.id = 2016051094
        And course.id = 1
        AND clazz.id = student.id_clazz 
    	AND course_clazz.id_clazz = clazz.id
        And course_clazz.id_course = course.id 
        And big_task.id_course = course.id 
        And small_task.big_task_id = big_task.id
        LIMIT 0,4;
        */ 
        @Select( " SELECT big_task.id AS bi, small_task.name AS sn, big_task.name AS bn, small_task.deadline, big_task.id_course , small_task.id FROM sbff.clazz,  sbff.student,sbff.course_clazz,sbff.course, sbff.big_task, sbff.small_task WHERE clazz.id = student.id_clazz  AND course_clazz.id_clazz = clazz.id And course_clazz.id_course = course.id  And big_task.id_course = course.id  And small_task.big_task_id = big_task.id AND student.id = #{studentId} And course.id = #{courseId} ORDER BY deadline desc LIMIT  #{offset}, #{limit}  ")
        @Results({
            @Result(property = "smallTaskName",column = "sn"),
            @Result(property = "bigTaskName",column = "bn"),
            @Result(property = "deadline",column = "deadline"),
            @Result(property = "courseId",column = "id_course"),
            @Result(property = "bigTaskId",column = "bi"),
            @Result(property = "id",column = "id"),
        })
        public List<SmallTask> findSmallTaskByCASId1(Integer courseId,Long studentId,Integer offset,Integer limit);

    




//今天之前的数据无分页

        @Select( " SELECT big_task.id AS bi, small_task.name AS sn, big_task.name AS bn, small_task.deadline, big_task.id_course , small_task.id FROM sbff.clazz,  sbff.student,sbff.course_clazz,sbff.course, sbff.big_task, sbff.small_task WHERE clazz.id = student.id_clazz  AND course_clazz.id_clazz = clazz.id And course_clazz.id_course = course.id  And big_task.id_course = course.id  And small_task.big_task_id = big_task.id AND student.id = #{studentId} And course.id = #{courseId}  and deadline<now()  ORDER BY deadline desc ")
        @Results({
            @Result(property = "smallTaskName",column = "sn"),
            @Result(property = "bigTaskName",column = "bn"),
            @Result(property = "deadline",column = "deadline"),
            @Result(property = "courseId",column = "id_course"),
            @Result(property = "bigTaskId",column = "bi"),
            @Result(property = "id",column = "id"),
        })
        public List<SmallTask> findBeforeNum(Integer courseId,Long studentId);
//今天之前的数据

        @Select( " SELECT big_task.id AS bi, small_task.name AS sn, big_task.name AS bn, small_task.deadline, big_task.id_course , small_task.id FROM sbff.clazz,  sbff.student,sbff.course_clazz,sbff.course, sbff.big_task, sbff.small_task WHERE clazz.id = student.id_clazz  AND course_clazz.id_clazz = clazz.id And course_clazz.id_course = course.id  And big_task.id_course = course.id  And small_task.big_task_id = big_task.id AND student.id = #{studentId} And course.id = #{courseId}  and deadline<now()  ORDER BY deadline desc LIMIT  #{offset}, #{limit}")
        @Results({
            @Result(property = "smallTaskName",column = "sn"),
            @Result(property = "bigTaskName",column = "bn"),
            @Result(property = "deadline",column = "deadline"),
            @Result(property = "courseId",column = "id_course"),
            @Result(property = "bigTaskId",column = "bi"),
            @Result(property = "id",column = "id"),
        })
        public List<SmallTask> findBefore(Integer courseId,Long studentId,Integer offset,Integer limit);
//今天之后的数据
        @Select( " SELECT big_task.id AS bi, small_task.name AS sn, big_task.name AS bn, small_task.deadline, big_task.id_course , small_task.id FROM sbff.clazz,  sbff.student,sbff.course_clazz,sbff.course, sbff.big_task, sbff.small_task WHERE clazz.id = student.id_clazz  AND course_clazz.id_clazz = clazz.id And course_clazz.id_course = course.id  And big_task.id_course = course.id  And small_task.big_task_id = big_task.id AND student.id = #{studentId} And course.id = #{courseId} and deadline>now() ORDER BY deadline desc ")
        @Results({
            @Result(property = "smallTaskName",column = "sn"),
            @Result(property = "bigTaskName",column = "bn"),
            @Result(property = "deadline",column = "deadline"),
            @Result(property = "courseId",column = "id_course"),
            @Result(property = "bigTaskId",column = "bi"),
            @Result(property = "id",column = "id"),
        })
        public List<SmallTask> findAfter(Integer courseId,Long studentId);







      /**
       * 
       * SELECT 
            score
        FROM
            sbff.score
        where
            id_student=2016051094
            and
            id_big_task=1
            and
            id_small_task=1
            and
            id_course = 1
       * 通过学生id和小项id和大项和课程id找成绩
       * @param studentId
       * @param courseId
       * @param bigId
       * @param smallId
       * @return
       */   
        @Select("SELECT  score FROM sbff.score where id_student=#{studentId} and id_big_task=#{bigId} and id_small_task=#{smallId} and id_course = #{courseId}")
        public Float findSmallTaskSocreByCASId(Long studentId,Integer courseId,Integer bigId ,Integer smallId);


        /**
         * 添加一条成绩记录
         * @param studentId
         * @param courseId
         * @param bigId
         * @param smallId
         * @param score
         */
        @Insert("INSERT INTO `sbff`.`score` (`id_student`, `id_course`, `id_big_task`, `id_small_task`,`score`) VALUES ('{studentId}', '{courseId}', '{bigId}', '{smallId}','{score}')")
        public void InsertTask(Long studentId,Integer courseId,Integer bigId ,Integer smallId,Float score);

        /**
         * 更新一条成绩
         * @param scoreId
         * @param score
         */

        @Update("UPDATE `sbff`.`score` SET `score` = ${score} WHERE (`id` = ${scoreId}) ")
        public void updateTask(Integer scoreId,Float score);

        /**
         * 获取成绩表id
         */
        @Select( " SELECT  id FROM  sbff.score where  id_student=#{studentId}  and  id_big_task=#{bigId}  and  id_small_task=#{smallId} and  id_course = #{courseId}")
        public Integer findScoreId(Long studentId,Integer courseId,Integer bigId ,Integer smallId);


}