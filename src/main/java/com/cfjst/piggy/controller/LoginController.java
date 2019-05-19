package com.cfjst.piggy.controller;

import java.util.Map;

import com.cfjst.piggy.bean.Student;
import com.cfjst.piggy.bean.Teacher;
import com.cfjst.piggy.service.StudentService;
import com.cfjst.piggy.service.TeacherService;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



/**
 * 登录请求
 */


@Controller
public class LoginController {

    @PostMapping("/login")
    public String login(Map<String,Object> map,
                        @RequestParam("id") Long id,
                        @RequestParam("password") String password,
                        @RequestParam("type") String type)  {

        
        if(!StringUtils.isEmpty(type)){
            if(type.equals("student")){

                //  学生认证
                StudentService service = new StudentService();
                Student student = service.login(id,password);
                if(student!=null){
                    return "student";
                }

            }else if (type.equals("teacher")){

                //  教师认证
                TeacherService service = new TeacherService();
                Teacher teacher = service.login(id,password);
                if(teacher!=null){
                    return "teacher";
                }

            }else if (type.equals("administer")){
                // TODO 管理员认证
            }
        }                    

        // 登录失败
        map.put("msg", "登录失败，请重新登录");
        map.put("id", id);
        map.put("type", type);
        return "login";
    }
    
}
