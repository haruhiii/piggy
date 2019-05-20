package com.cfjst.piggy.service;

import static org.junit.Assert.assertEquals;



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
        Long id=Long.valueOf(2016051094);
        /** 登陆失败测试 */
        assertEquals(null, service.login(id,"123455"));
        /** 登陆成功测试 */
        assertEquals(id, service.login(id,"123456").getId());
    }
    
}
