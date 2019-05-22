package com.cfjst.piggy.service;

import java.util.List;

import com.cfjst.piggy.bean.Course;
import com.cfjst.piggy.bean.CTC;
import com.cfjst.piggy.dao.CourseDao;
import com.cfjst.piggy.dao.CtcDao;
import com.cfjst.piggy.util.SqlUtil;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
@Service
public class CourseService {

    SqlSession sqlSession;
    CourseDao dao;
    CtcDao ctcDao;

    public CourseService(){
        sqlSession = new SqlUtil().openSession();
        dao = sqlSession.getMapper(CourseDao.class);
        ctcDao = sqlSession.getMapper(CtcDao.class);
    }
    public List<Course>  findByStudentId( Long id){
        //通过学生Id获取课程信息
        List<Course> courses = dao.findByStudentId(id);
        return courses;
      
    }

    public List<CTC> findAllCourse() {

		return ctcDao.findAllCourse();
	}

	public void addCTC(CTC ctc)  {
		ctcDao.addCTC(ctc);
	}
    public void addCourse(Course course) {
		ctcDao.addCourse(course);
	}
    
    public void updateCTC(CTC ctc) {
    	
		ctcDao.updateCTC(ctc);
    	
	}
    public void updateCourse(Course course)  {
		ctcDao.updateCourse(course);
    	
	}

    public CTC findByCTCId(int id) {
		return ctcDao.findByCTCId(id);
	}
    
    public void deleteCourse(int id)  {
		ctcDao.deleteCourse(id);
	}
}