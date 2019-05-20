package com.cfjst.piggy.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cfjst.piggy.bean.Course;
import com.cfjst.piggy.bean.Student;
import com.cfjst.piggy.bean.Task;
import com.cfjst.piggy.service.CourseService;
import com.cfjst.piggy.service.StudentService;
import com.cfjst.piggy.service.TaskService;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;



/**
 * 通用请求
 */


@Controller
public class IndexController {

	@RequestMapping("/")
	public String index(Map<String,Object> map)  {




		StudentService service = new StudentService();
		Long id=Long.valueOf(2016051093);
			
		Student student = service.login(id,"123456");
		student.getCourses();
		map.put("student",student );
		System.out.println(student.getName());
		return "index";
	}
	 
	@RequestMapping("/login")
	public String login()  {

		return "login";
	}

	@RequestMapping("/student")
	public String student( Map<String,Object> map )  {

		Student student =  (Student)SecurityUtils.getSubject().getPrincipal();
		//  List<Course> courses = student.getCourses();
		map.put("student",student);
		  
		  
		  // CourseService service = new CourseService();
		  // List<Course> courses = service.findByStudentId(id);
		  // redirectAttributes.addFlashAttribute("course", courses);

		//  本页刷新异常 已解决

		// List<Course> courses = (List<Course>) redirectAttributes.getFlashAttributes().get("course");
		// for(Course course :courses){
		// 	System.err.println(course.getName());
		// }
		// map.put("courses", courses);

		return "student/index";
	}


	@GetMapping("student/course")
	public String course(Integer courseId,Map<String,Object> map)  {
		System.out.println("-----------------"+courseId);

		Student student =  (Student)SecurityUtils.getSubject().getPrincipal();
		//  List<Course> courses = student.getCourses();
		TaskService service = new TaskService();
		System.out.println(courseId);
		// TODO 还没改
        List<Task> tasks = service.getSmallTaskByCASId( 2 , student.getId());
        
		map.put("tasks",tasks);
		return "student/course";
	}
	
}
