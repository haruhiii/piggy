package com.cfjst.piggy.controller;

import com.cfjst.piggy.bean.Student;
import com.cfjst.piggy.bean.Teacher;
import com.cfjst.piggy.service.StudentService;
import com.cfjst.piggy.service.TeacherService;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



/**
 * 登录请求
 */


@Controller
public class LoginController {

    @PostMapping("/login")
    public String login(@RequestParam("id") Long id,
                        @RequestParam("password") String password,
                        @RequestParam("type") String type)  {

        System.out.println(password);
        System.out.println(id);
        
        if(!StringUtils.isEmpty(type)){
            if(type.equals("student")){


                //  学生认证

                StudentService service = new StudentService();
                Student stu = new Student();
                stu.setId(id);
                stu.setPassword(password);
                Student student = service.login(stu);
                if(service.login(student)!=null){
                    return "student";
                }

            }else if (type.equals("teacher")){

                //  教师认证

                TeacherService service = new TeacherService();
                Teacher tea = new Teacher();
                tea.setId(id);
                tea.setPassword(password);
                Teacher teacher = service.login(tea);
                if(service.login(teacher)!=null){
                    return "teacher";
                }

            }else if (type.equals("administer")){
                // TODO 管理员认证
            }
        }                    

        // 登录失败
        return "login";
    }
    
}
