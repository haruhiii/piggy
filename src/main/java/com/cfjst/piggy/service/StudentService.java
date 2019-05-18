package com.cfjst.piggy.service;

import com.cfjst.piggy.bean.Student;
import com.cfjst.piggy.dao.StudentDao;
import com.cfjst.piggy.util.SqlUtil;

import org.apache.ibatis.session.SqlSession;

public class StudentService {

    SqlSession sqlSession;
    StudentDao dao;

    public StudentService(){
        sqlSession = new SqlUtil().openSession();
        dao = sqlSession.getMapper(StudentDao.class);
    }

    boolean login(Student student){
        Student stu = dao.findById(student.getId());
        if(null!=stu){
            if(stu.getPassword().equals(student.getPassword())){
                return true;
            }
        }
        return false;
        
    }
}