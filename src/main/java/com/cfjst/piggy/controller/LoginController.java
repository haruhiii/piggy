package com.cfjst.piggy.controller;

import java.util.List;
import java.util.Map;

import javax.security.auth.login.AccountNotFoundException;

import com.cfjst.piggy.bean.Course;
import com.cfjst.piggy.bean.Student;
import com.cfjst.piggy.bean.Teacher;
import com.cfjst.piggy.service.CourseService;
import com.cfjst.piggy.service.StudentService;
import com.cfjst.piggy.service.TeacherService;
import com.cfjst.piggy.shiro.UserToken;

import org.apache.catalina.User;
import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;



/**
 * 登录请求
 */


@Controller
public class LoginController {
    @PostMapping("/login")
    public RedirectView  login(
                        @RequestParam("id") Long id,
                        @RequestParam("password") String password,
                        @RequestParam("type") String type,
                        Boolean rememberMe,
                        RedirectAttributes redirectAttributes,
                        Map<String,Object> map)  {

        RedirectView redirectTarget = new RedirectView();
        if(rememberMe==null){
            rememberMe=false;
        }
        System.out.println(rememberMe);
        redirectTarget.setContextRelative(true);



        Subject subject = SecurityUtils.getSubject();

        UserToken token = new UserToken(id,password,type);
        token.setRememberMe(rememberMe);
        try {
            //跳转至 shiro 认证操作
            subject.login(token);
            if(token.getType().equals("Student")){

                Student student =  (Student)subject.getPrincipal();
              //  List<Course> courses = student.getCourses();
                // redirectAttributes.addFlashAttribute("student",student);
                
                
                // CourseService service = new CourseService();
                // List<Course> courses = service.findByStudentId(id);
                // redirectAttributes.addFlashAttribute("course", courses);
                redirectTarget.setUrl("/student");
              //  return "redirect:/student";
            }else{                
                redirectTarget.setUrl("/teacher");

                // return "redirect:/teacher";
                
            }
            // TODO 管理员认证


        } catch (UnknownAccountException e) { 
            //账号不存在 焦点账号框
            redirectAttributes.addFlashAttribute("id", id);   
            redirectAttributes.addFlashAttribute("type", type);                  
            redirectAttributes.addFlashAttribute("msg", "账号不存在！");            
            redirectAttributes.addFlashAttribute("focus", "id");      
            // return "login";
            redirectTarget.setUrl("/login");


        } catch(IncorrectCredentialsException e){
            //密码错误 焦点密码框
            redirectAttributes.addFlashAttribute("msg", "密码错误");         
            redirectAttributes.addFlashAttribute("id", id);   
            redirectAttributes.addFlashAttribute("type", type);                     
            redirectAttributes.addFlashAttribute("focus", "password");    
            // return "login";
            redirectTarget.setUrl("/login");
        }

        return redirectTarget;



        // // if(!StringUtils.isEmpty(type)){
        //     if(type.equals("student")){

        //         //  学生认证
        //         StudentService service = new StudentService();
        //         Student student = service.login(id,password);
        //         if(student!=null){
        //             return "student/index";
        //         }

        //     }else if (type.equals("teacher")){

        //         //  教师认证
        //         TeacherService service = new TeacherService();
        //         Teacher teacher = service.login(id,password);
        //         if(teacher!=null){
        //             return "teacher/index";
        //         }

        //     }else if (type.equals("administer")){
        //         // TODO 管理员认证
        //     }
        // // }                    

        // // 登录失败
        // map.put("msg", "登录失败 请重试！");
        // map.put("id", id);
        // map.put("type", type);
        // return "login";
    }
    
}
