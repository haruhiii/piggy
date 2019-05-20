package com.cfjst.piggy.service;

import java.util.List;

import com.cfjst.piggy.bean.BigTask;
import com.cfjst.piggy.dao.BigTaskDao;
import com.cfjst.piggy.util.SqlUtil;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
@Service
public class BigTaskService {

    SqlSession sqlSession;
    BigTaskDao dao;

    public BigTaskService() {
        sqlSession = new SqlUtil().openSession();
        dao = sqlSession.getMapper(BigTaskDao.class);
    }



    public List<BigTask> getBigTaskByCASId(Integer courseId,Long studentId){
        //通过学生Id和课程id获取大项

        List<BigTask> tasks = dao.findBigTaskByCASId(courseId,studentId);
        return tasks;
    }


}