package com.cfjst.piggy.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import com.cfjst.piggy.bean.Course;
import com.cfjst.piggy.bean.Student;
import com.cfjst.piggy.bean.Task;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 课程服务类测试
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskServiceTests {
    

    /**
     * 根据学号和课程找作业
     */
    @Test
    public void findByStudentIdTest() {
       
		//  List<Course> courses = student.getCourses();
		TaskService service = new TaskService();
        List<Task> tasks = service.getSmallTaskByCASId(  Integer.valueOf(1), Long.valueOf(2016051094));
        for(Task task:tasks){
            System.out.println(task.getSmallTaskName());

        }
    }
    
}
