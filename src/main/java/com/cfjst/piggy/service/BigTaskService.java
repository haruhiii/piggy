package com.cfjst.piggy.service;

import java.util.ArrayList;
import java.util.List;

import com.cfjst.piggy.bean.BigTask;
import com.cfjst.piggy.bean.SmallTask;
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


    /**
     * 获取各个大项的得分百分比
     * @param studentId
     * @param courseId
     * @param bigTasks
     * @return
     */
    public List<Integer> getBigTaskPercentBySSId(Long studentId,Integer courseId,List<BigTask> bigTasks){
        
        List<Integer> persents = new ArrayList<Integer>();
        for(BigTask bigTask :bigTasks){

            // TODO
            if(bigTask.getId()==3){
                System.out.println("777777");
            }
            //取到一个大项的平均值
            Integer percent = dao.getBigTaskAvg(courseId, studentId, bigTask.getId());

            if(percent==null){
                percent=0;
            }
            bigTask.setPercent(percent);
            persents.add(percent);
        }
        
        return persents;
    }
    public List<BigTask> getBigTaskByCASId(Integer courseId,Long studentId){
        //通过学生Id和课程id获取大项

        List<BigTask> tasks = dao.findBigTaskByCASId(courseId,studentId);
        
        return tasks;
    }


}