package com.cfjst.piggy.service;

import java.util.List;

import com.cfjst.piggy.bean.BigTask;
import com.cfjst.piggy.bean.Course;
import com.cfjst.piggy.bean.SmallTask;
import com.cfjst.piggy.bean.SmallTask;
import com.cfjst.piggy.bean.Teacher;
import com.cfjst.piggy.dao.CourseDao;
import com.cfjst.piggy.dao.TaskDao;
import com.cfjst.piggy.dao.TeacherDao;
import com.cfjst.piggy.util.SqlUtil;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
@Service
public class TaskService {

    SqlSession sqlSession;
    TaskDao dao;

    public TaskService() {
        sqlSession = new SqlUtil().openSession();
        dao = sqlSession.getMapper(TaskDao.class);
    }

    public  List<SmallTask>  getSmallTaskByCASId( Integer courseId,Long studentId){
        //通过学生Id和课程id获取小项
        List<SmallTask> tasks = dao.findSmallTaskByCASId(courseId,studentId);
        return tasks;
    }

    public List<BigTask> getBigTaskByCASId(Integer courseId,Long studentId){
        //通过学生Id和课程id获取大项

        List<BigTask> tasks = dao.findBigTaskByCASId(courseId,studentId);
        return tasks;
    }


}