package com.cfjst.piggy.dao;

import static org.mockito.ArgumentMatchers.isNull;



import com.cfjst.piggy.bean.Student;
import com.cfjst.piggy.bean.Teacher;
import com.cfjst.piggy.util.SqlUtil;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 数据库接口测试
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTests {
    


    @Test
    public void findStudentByIdTest()  {
        //查找学生测试
        SqlSession sqlSession = new SqlUtil().openSession();
        UserDao dao = sqlSession.getMapper(UserDao.class);
        Integer id = 2016051094;
        Student student = dao.findStudentById(id);
        if(student!=isNull()){
            System.out.println("findStudentByIdTest(黑富福):" + student.getName() );
        }else{
            System.out.println("null");
        }
        sqlSession.close();
    }

    @Test
    public void findTeacherByIdTest()  {
        //查找教师测试
        SqlSession sqlSession = new SqlUtil().openSession();
        UserDao dao = sqlSession.getMapper(UserDao.class);
        Integer id = 10003;
        Teacher teacher = dao.findTeacherById(id);
        if(teacher!=isNull()){
            System.out.println("findTeacherByIdTest(华郑):" + teacher.getName() );
        }else{
            System.out.println("null");
        }
        sqlSession.close();
    }


    @Test
    public void addStudentTest()  {
        //添加学生测试
        SqlSession sqlSession = new SqlUtil().openSession();
        UserDao dao = sqlSession.getMapper(UserDao.class);
        Integer id = 1111111111;
        Student student = new Student(id,"123456","黑富福");
        dao.addStudent(student);        
        dao.delStudentById(id);
        sqlSession.close();
    }

    @Test
    public void addTeacherTest()  {
        //添加教师测试
        SqlSession sqlSession = new SqlUtil().openSession();
        UserDao dao = sqlSession.getMapper(UserDao.class);
        Integer id = 11111;
        Teacher teacher = new Teacher(id,"123456","华郑");
        dao.addTeacher(teacher);        
        dao.delTeacherById(id);
        sqlSession.close();
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
