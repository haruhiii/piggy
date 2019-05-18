package com.cfjst.piggy.service;

import com.cfjst.piggy.bean.Teacher;
import com.cfjst.piggy.dao.TeacherDao;
import com.cfjst.piggy.util.SqlUtil;

import org.apache.ibatis.session.SqlSession;

public class TeacherService {

    SqlSession sqlSession;
    TeacherDao dao;

    public TeacherService() {
        sqlSession = new SqlUtil().openSession();
        dao = sqlSession.getMapper(TeacherDao.class);
    }

    boolean login(Teacher teacher){
        Teacher tea = dao.findById(teacher.getId());
        if(null!=tea){
            if(tea.getPassword().equals(teacher.getPassword())){
                return true;
            }
        }
        return false;
        
    }
}