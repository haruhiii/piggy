package com.cfjst.piggy.service;

import java.util.List;

import com.cfjst.piggy.bean.Course;
import com.cfjst.piggy.bean.Task;
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

    public  List<Task>  getSmallTaskByCASId( Integer courseId,Long student_id){
        //通过学生Id获取课程信息
        List<Task> tasks = dao.findSmallTaskByCASId(courseId,student_id);
        return tasks;
    }
}