package com.cfjst.piggy.service;

import java.util.List;

import com.cfjst.piggy.bean.Course;
import com.cfjst.piggy.bean.Student;
import com.cfjst.piggy.dao.StudentDao;
import com.cfjst.piggy.util.SqlUtil;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    SqlSession sqlSession;
    StudentDao dao;

    public StudentService(){
        sqlSession = new SqlUtil().openSession();
        dao = sqlSession.getMapper(StudentDao.class);
    }


    /**
     * 登录
     * @param student 学生信息，至少包含Id和密码
     * @return 是否登陆成功
     */
    public Student login(Long id, String password){
        Student stu = dao.findById(id);
        if(null!=stu){
            if(stu.getPassword().equals(password)){
                return stu;
            }
        }
        return null;
        
    }
    public Student getById(Long id){
        
        return dao.findById(id);
    }

    public List<Course>  getCourses( Long id){
        //通过学生Id获取课程信息
        CourseService service = new CourseService();
        List<Course> courses = service.findByStudentId(id);
        return courses;
      
    } 

}