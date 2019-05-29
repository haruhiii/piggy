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

    public  List<SmallTask>  getSmallTaskByCASId1(Integer courseId,Long studentId,Integer page,Integer limit){
        //通过学生Id和课程id获取小项分页
        return dao.findSmallTaskByCASId1(courseId,studentId,(page-1)*limit,limit);
    }
    public  List<SmallTask>  getSmallTaskByCASId(Integer courseId,Long studentId){
        //通过学生Id和课程id获取小项
        return dao.findSmallTaskByCASId(courseId,studentId);
    }


    //查找deadline是否过期的数据
    public  List<SmallTask>  getBeforeNum(Integer courseId,Long studentId){
        //之前
        return dao.findBeforeNum(courseId,studentId);
    } 
    public  List<SmallTask>  getBefore(Integer courseId,Long studentId,Integer page,Integer limit){
        //之前
        return dao.findBefore(courseId,studentId, (page-1)*limit, limit);
    } public  List<SmallTask>  getAfter(Integer courseId,Long studentId){
        //之后
        return dao.findAfter(courseId, studentId);
    }


    public Float  getSmallTaskScoreByCASId(Long studentId,Integer courseId,Integer bigId ,Integer smallId){
        //       * 通过学生id和小项id和大项和课程id找成绩
        return dao.findSmallTaskSocreByCASId(studentId, courseId, bigId, smallId);
    }
    public void autoSetSmallTaskScore(Long studentId,List<SmallTask> smallTasks){
        //自动设置分数
        for(SmallTask smallTask:smallTasks){
            Float score = getSmallTaskScoreByCASId(studentId,smallTask.getCourseId() , smallTask.getBigTaskId(), smallTask.getId());
            if(score!=null){
                smallTask.setScore(score);
            }else{//分数为-2代表未提交
                smallTask.setScore(-2f);
            }
        }
    }


    //插入一条新成绩
    public void InsertTask(Long studentId,Integer courseId,Integer bigId ,Integer smallId,Float score){
        dao.InsertTask(studentId, courseId, bigId, smallId, score);
    }

    //更新一条成绩
    public boolean updateTask(Long studentId,Integer courseId,Integer bigId ,Integer smallId,Float score){
        Integer scoreId = dao.findScoreId(studentId, courseId, bigId, smallId);
        if(scoreId!=null){
            System.out.println(scoreId);
            dao.updateTask(scoreId, score);
            return true;
        }else{
            return false;
        }
    }
}