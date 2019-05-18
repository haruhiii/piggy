package com.cfjst.piggy.dao;

import static org.junit.Assert.assertEquals;


import com.cfjst.piggy.bean.Clazz;
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
public class ClazzDaoTests {
    SqlSession sqlSession;
    ClazzDao dao;

    @Before
    public void init(){
        sqlSession = new SqlUtil().openSession();
        dao = sqlSession.getMapper(ClazzDao.class);
    }
    @After
    public void done(){
        sqlSession.close();
    }


    @Test
    public void findByIdTest()  {
        //获取班级信息测试，不包括课程信息
        Integer id = 1;
        Clazz clazze = dao.findByClazzId(id);
        assertEquals("计算机科学与技术161",clazze.getName());

        // for(Course course:clazze.getCourses()){
        //     System.out.println(course.getName());
        // }
        
    }

    @Test
    public void  findByClazzIdWithCourseTests(){
        //获取班级的所有信息测试，包括课程信息
        Integer id = 2;
        Clazz clazze = dao.findByClazzIdWithCourse(id);
        assertEquals(3,clazze.getCourses().size());        
        assertEquals("计算机科学与技术162",clazze.getName());

        // for(Course course:clazze.getCourses()){
        //     System.out.println(course.getName());
        // }
    }
}
