package com.cfjst.piggy.service;

import java.util.List;

import com.cfjst.piggy.bean.Course;
import com.cfjst.piggy.dao.CourseDao;
import com.cfjst.piggy.util.SqlUtil;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
@Service
public class CourseService {

    SqlSession sqlSession;
    CourseDao dao;

    public CourseService(){
        sqlSession = new SqlUtil().openSession();
        dao = sqlSession.getMapper(CourseDao.class);
    }
    public List<Course>  findByStudentId( Long id){
        //通过学生Id获取课程信息
        List<Course> courses = dao.findByStudentId(id);
        return courses;
      
    }
}