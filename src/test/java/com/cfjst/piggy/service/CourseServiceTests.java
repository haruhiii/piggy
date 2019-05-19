package com.cfjst.piggy.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import com.cfjst.piggy.bean.Course;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 课程服务类测试
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseServiceTests {
    

    /**
     * 根据学号找课程
     */
    @Test
    public void findByStudentIdTest() {
        CourseService service = new CourseService();
        Long id=Long.valueOf(2016051094);
        List<Course> courses = service.findByStudentId(id);

        for(Course course:courses){

            System.out.println(course.getName());
        }
    }
    
}
