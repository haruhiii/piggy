package com.cfjst.piggy.dao;


import static org.junit.Assert.assertEquals;


import com.cfjst.piggy.bean.Student;
import com.cfjst.piggy.util.SqlUtil;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 学生数据库接口测试
 */

@RunWith(SpringRunner.class)
@SpringBootTest

public class StudentDaoTests {
        

    SqlSession sqlSession;
    StudentDao dao;

    @Before
    public void init(){
        sqlSession = new SqlUtil().openSession();
        dao = sqlSession.getMapper(StudentDao.class);
    }
    @After
    public void done(){
        sqlSession.close();
    }

    @Test
    public void findByIdTest()  {
        //查找学生测试

        Integer id = 2016051094;
        assertEquals("黑富福", dao.findById(id).getName());

    }

    @Test
    public void addTest()  {
        //添加、删除学生测试
        Integer id = 1111111111;
        Student student = new Student(id,"123456","绿富福");        
        dao.add(student);        
        assertEquals("绿富福", dao.findById(id).getName());

        dao.delById(id);
        assertEquals(null, dao.findById(id));
    }


    @Test
    public void updateTest()  {
        //修改学生信息测试
        Integer id = 2016051094;
        Student student = new Student(id,"123456","白富福");        
        dao.update(student);        
        assertEquals("白富福", dao.findById(id).getName());
        student.setName("黑富福");
        dao.update(student);        
        assertEquals("黑富福", dao.findById(id).getName());
    }
   
    
    // @Test
    // public void delStudentByIdTest()  {
    //     //删除学生测试
    //     SqlSession sqlSession = new SqlUtil().openSession();
    //     UserDao dao = sqlSession.getMapper(UserDao.class);
    //     Integer id = 2016051094;
    //     dao.delStudentById(id);
    //     sqlSession.close();
    // }

    // @Test
    // public void delTeacherByIdTest()  {
    //     //删除教师测试
    //     SqlSession sqlSession = new SqlUtil().openSession();
    //     UserDao dao = sqlSession.getMapper(UserDao.class);
    //     Integer id = 10003;
    //     dao.delTeacherById(id);
    //     sqlSession.close();
    // }

}
