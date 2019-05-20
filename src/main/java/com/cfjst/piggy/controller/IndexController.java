package com.cfjst.piggy.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cfjst.piggy.bean.BigTask;
import com.cfjst.piggy.bean.SmallTask;
import com.cfjst.piggy.bean.Student;
import com.cfjst.piggy.service.BigTaskService;
import com.cfjst.piggy.service.SmallTaskService;
import com.cfjst.piggy.service.StudentService;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 通用请求
 */

@Controller
public class IndexController {

	@RequestMapping("/")
	public String index(Map<String, Object> map) {

		StudentService service = new StudentService();
		Long id = Long.valueOf(2016051093);

		Student student = service.login(id, "123456");
		student.getCourses();
		map.put("student", student);
		System.out.println(student.getName());
		return "index";
	}

	@RequestMapping("/login")
	public String login() {

		return "login";
	}

	@RequestMapping("/student")
	public String student(Map<String, Object> map) {

		Student student = (Student) SecurityUtils.getSubject().getPrincipal();
		// List<Course> courses = student.getCourses();
		map.put("student", student);

		// CourseService service = new CourseService();
		// List<Course> courses = service.findByStudentId(id);
		// redirectAttributes.addFlashAttribute("course", courses);

		// 本页刷新异常 已解决

		// List<Course> courses = (List<Course>)
		// redirectAttributes.getFlashAttributes().get("course");
		// for(Course course :courses){
		// System.err.println(course.getName());
		// }
		// map.put("courses", courses);

		return "student/index";
	}

	/**
	 * 课程页面
	 * @param courseId 需要查询的课程Id
	 * @param map
	 * @return
	 */
	@GetMapping("student/course")
	public String course(@RequestParam("courseId") Integer courseId, Map<String, Object> map) {


		BigTaskService service = new BigTaskService();
		
		Student student = (Student) SecurityUtils.getSubject().getPrincipal();
		// List<Course> courses = student.getCourses();
		//   还没改 已改


		List<SmallTask> tasks = new SmallTaskService().getSmallTaskByCASId(courseId, student.getId());
		List<BigTask> bigTasks =  service.getBigTaskByCASId(courseId, student.getId());
		List<Integer> percents = service.getBigTaskPercentBySSId(student.getId(), courseId,bigTasks);
	 
		map.put("tasks",tasks);
		map.put("bigTasks",bigTasks);
		map.put("percents",percents);

		return "student/course";
	}
	
}
