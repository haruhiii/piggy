package com.cfjst.piggy.service;

import static org.junit.Assert.assertEquals;

import com.cfjst.piggy.bean.Teacher;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 教师服务类测试
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class TeacherServiceTests {
    

    /**
     * 登录测试
     */
    @Test
    public void loginTest() {
        TeacherService service = new TeacherService();
        Teacher teacher = new Teacher(Long.valueOf(10003), "123455","");
        /** 登陆失败测试 */
        assertEquals(false, service.login(teacher));
        teacher.setPassword("123456");
        /** 登陆成功测试 */
        assertEquals(true, service.login(teacher));
    }
    
}
