package com.cfjst.piggy.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import com.cfjst.piggy.bean.BigTask;
import com.cfjst.piggy.bean.Course;
import com.cfjst.piggy.bean.Student;
import com.cfjst.piggy.bean.SmallTask;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 课程服务类测试
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class BigTaskServiceTests {
    

   

    /**
     * 根据学号和课程找大作业测试
     */
    @Test

    public void findBigTaskByCASId() {
        BigTaskService service = new BigTaskService();

        List<BigTask> tasks =   service.getBigTaskByCASId( Integer.valueOf(1), Long.valueOf(2016051094));
        for(BigTask task:tasks){
            System.out.println(task.getName());

        }
    }


    
}
