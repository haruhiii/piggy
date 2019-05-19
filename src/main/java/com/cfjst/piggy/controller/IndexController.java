package com.cfjst.piggy.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cfjst.piggy.bean.Course;
import com.cfjst.piggy.bean.Student;
import com.cfjst.piggy.service.CourseService;
import com.cfjst.piggy.service.StudentService;

import org.springframework.stereotype.Controller;
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
	public String student( Map<String,Object> map
		,RedirectAttributes redirectAttributes)  {
								

		// TODO 本页刷新异常

		// List<Course> courses = (List<Course>) redirectAttributes.getFlashAttributes().get("course");
		// for(Course course :courses){
		// 	System.err.println(course.getName());
		// }
		// map.put("courses", courses);

		return "student/index";
	}
	@RequestMapping("/course")
	public String course()  {
		return "student/course";
	}
	
}
