package com.cfjst.piggy.dao;


import static org.junit.Assert.assertEquals;


import com.cfjst.piggy.bean.Teacher;
import com.cfjst.piggy.util.SqlUtil;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 教师数据库接口测试
 */

@RunWith(SpringRunner.class)
@SpringBootTest

public class TeacherDaoTests {
        

    SqlSession sqlSession;
    TeacherDao dao;

    @Before
    public void init(){
        sqlSession = new SqlUtil().openSession();
        dao = sqlSession.getMapper(TeacherDao.class);
    }
    @After
    public void done(){
        sqlSession.close();
    }

    @Test
    public void findByIdTest()  {
        //查找测试

        Integer id = 10003;
        assertEquals("华郑", dao.findById(id).getName());

    }


    @Test
    public void addTest()  {
        //添加、删除测试
        Integer id = 11111;
        Teacher teacher = new Teacher(id,"123456","华斜");        
        dao.add(teacher);        
        assertEquals("华斜", dao.findById(id).getName());
        dao.delById(id);
        assertEquals(null, dao.findById(id));
    }


    @Test
    public void updateTest()  {
        //修改信息测试
        Integer id = 10003;
        Teacher teacher = new Teacher(id,"123456","华歪");        
        dao.update(teacher);        
        assertEquals("华歪", dao.findById(id).getName());
        teacher.setName("华郑");
        dao.update(teacher);        
        assertEquals("华郑", dao.findById(id).getName());
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
