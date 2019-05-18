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

    /**
     * 登录
     * @param student 教师信息，至少包含Id和密码
     * @return 是否登陆成功
     */
    public Teacher login(Teacher teacher){
        Teacher tea = dao.findById(teacher.getId());
        if(null!=tea){
            if(tea.getPassword().equals(teacher.getPassword())){
                return tea;
            }
        }
        return null;
        
    }
}