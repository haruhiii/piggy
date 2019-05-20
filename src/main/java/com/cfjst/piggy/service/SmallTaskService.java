package com.cfjst.piggy.service;

import java.util.List;

import com.cfjst.piggy.bean.SmallTask;
import com.cfjst.piggy.dao.SmallTaskDao;
import com.cfjst.piggy.util.SqlUtil;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
@Service
public class SmallTaskService {

    SqlSession sqlSession;
    SmallTaskDao dao;

    public SmallTaskService() {
        sqlSession = new SqlUtil().openSession();
        dao = sqlSession.getMapper(SmallTaskDao.class);
    }

    public  List<SmallTask>  getSmallTaskByCASId( Integer courseId,Long studentId){
        //通过学生Id和课程id获取小项
        List<SmallTask> tasks = dao.findSmallTaskByCASId(courseId,studentId);
        return tasks;
    }


}