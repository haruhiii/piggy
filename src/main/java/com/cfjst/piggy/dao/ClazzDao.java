package com.cfjst.piggy.dao;

import java.util.List;

import com.cfjst.piggy.bean.Clazz;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;


public interface ClazzDao {



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


}