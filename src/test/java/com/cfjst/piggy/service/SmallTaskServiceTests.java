package com.cfjst.piggy.service;


import java.util.List;

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
public class SmallTaskServiceTests {
    

    /**
     * 根据学号和课程找小作业测试
     */
    @Test
    public void findByStudentIdTest() {
       
        Long studentId = 2016051094l;
		//  List<Course> courses = student.getCourses();
		SmallTaskService service = new SmallTaskService();
        List<SmallTask> smallTasks = service.getSmallTaskByCASId(  Integer.valueOf(1), studentId);
        service.autoSetSmallTaskScore(studentId, smallTasks);
        for(SmallTask task:smallTasks){
          System.out.println(task.getScore());
        }
    }
    @Test

    public void updateTest() {
       
        Long studentId = 2016051094l;
        Integer courseId=1;
        Integer bigId=1;
        Integer smallId=1;
        Float score=88f;
		//  List<Course> courses = student.getCourses();
		SmallTaskService service = new SmallTaskService();
        System.out.println(service.updateTask(studentId, courseId, bigId, smallId, score)); 
    }


    // if(!){
    //     service.InsertTask(student.getId(), courseId, bigId, smallId, -1f);
    
}
