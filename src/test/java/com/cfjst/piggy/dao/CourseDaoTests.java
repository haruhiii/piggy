package com.cfjst.piggy.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import com.cfjst.piggy.bean.Clazz;
import com.cfjst.piggy.bean.Course;
import com.cfjst.piggy.util.SqlUtil;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * 班级数据库接口测试
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseDaoTests {
    SqlSession sqlSession;
    CourseDao dao;

    @Before
    public void init(){
        sqlSession = new SqlUtil().openSession();
        dao = sqlSession.getMapper(CourseDao.class);
    }


    @Test
    public void findByClazzIdTests(){
        //通过班级Id获取课程信息测试
        Integer id = 2;
        List<Course> courses = dao.findByClazzId(id);

        for(Course course:courses){
            System.out.println(course.getId());
        }
    }

    @Test
    public void findByStudentIdTests(){
        //通过学生Id获取课程信息测试
        Integer id = 2016051093;
        List<Course> courses = dao.findByStudentId(id);

        for(Course course:courses){
            System.out.println(course.getId());
        }
    }
}
