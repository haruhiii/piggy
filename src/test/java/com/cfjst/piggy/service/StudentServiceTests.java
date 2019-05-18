package com.cfjst.piggy.service;

import static org.junit.Assert.assertEquals;



import com.cfjst.piggy.bean.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 学生服务类测试
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentServiceTests {
    

    /**
     * 登录测试
     */
    @Test
    public void loginTest() {
        StudentService service = new StudentService();
        Student student = new Student();
        Long id=Long.valueOf(2016051094);
        student.setId(id);
        student.setPassword("123455");
        /** 登陆失败测试 */
        assertEquals(null, service.login(student));
        student.setPassword("123456");
        /** 登陆成功测试 */
        assertEquals(id, service.login(student).getId());
    }
    
}
