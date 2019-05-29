package com.cfjst.piggy.controller;

import java.security.Provider.Service;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.cfjst.piggy.bean.BigTask;
import com.cfjst.piggy.bean.Course;
import com.cfjst.piggy.bean.SmallTask;
import com.cfjst.piggy.bean.Student;
import com.cfjst.piggy.bean.User;
import com.cfjst.piggy.service.BigTaskService;
import com.cfjst.piggy.service.CourseService;
import com.cfjst.piggy.service.SmallTaskService;
import com.cfjst.piggy.service.StudentService;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.boot.jackson.JsonObjectSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 通用请求
 */

@Controller
public class IndexController {

	@RequestMapping("/")
	public String index() {
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		System.out.println(user);
		if (user != null) {
			if (user.getId() > 1000000l) {
				// 自动跳转到学生页面
				return "redirect:student";
			}
			if (user.getId() > 99999) {
				// 自动跳转到教师页面

				return "redirect:teacher";
			}
		}

		return "login";
	}

	// 之后可以删除这个mapping，已经基本实现自动跳转
	@RequestMapping("/login")
	public String login() {

		return "login";
	}

	@RequestMapping("/student")
	public String student(Map<String, Object> map) {

		Student student = (Student) SecurityUtils.getSubject().getPrincipal();
		// List<Course> courses = student.getCourses();

		// 本页刷新异常 已解决
		CourseService courseService = new CourseService();
		List<Course> courses = courseService.findByStudentId(student.getId());
		for (Course course : courses) {
			System.err.println(course.getName());
		}
		BigTaskService bigTaskService = new BigTaskService();
		List<BigTask> bigTasks;
		// 设置课程颜色
		for (Course course : courses) {
			bigTasks = bigTaskService.getBigTaskByCASId(course.getId(), student.getId());
			bigTaskService.getBigTaskPercentBySSId(student.getId(), course.getId(), bigTasks);
			boolean flag = false;
			course.setWorningType("green");
			course.setWorningText("相对优秀");
			for (BigTask bigTask : bigTasks) {
				if (bigTask.getPercent() != 0 && bigTask.getPercent() < 60) {
					course.setWorningType("red");
					course.setWorningText("极可能挂科！");
					System.out.println("setRed");
					// TODO 删除
					flag = true;
				}

			}
			if (!flag) {
				for (BigTask bigTask : bigTasks) {
					if (bigTask.getPercent() != 0 && bigTask.getPercent() < 80) {
						course.setWorningText("可能会挂哦~");
						course.setWorningType("orange");
					}
				}
			}

			// courses.add(course);
		}

		for (Course course : courses) {
			System.out.println(course.getName());
			System.out.println(course.getWorningType());

		}

		map.put("courses", courses);
		map.put("student", student);

		return "student/index";
	}

	/**
	 * 课程页面
	 * 
	 * @param courseId 需要查询的课程Id
	 * @param map
	 * @return
	 */
	@GetMapping("student/course")
	public String course(@RequestParam("courseId") Integer courseId, Map<String, Object> map) {

		BigTaskService service = new BigTaskService();
		SmallTaskService service2 = new SmallTaskService();

		Student student = (Student) SecurityUtils.getSubject().getPrincipal();
		// List<Course> courses = student.getCourses();
		// 还没改 已改

		List<SmallTask> tasks = service2.getSmallTaskByCASId(courseId, student.getId());
		service2.autoSetSmallTaskScore(student.getId(), tasks);
		List<BigTask> bigTasks = service.getBigTaskByCASId(courseId, student.getId());
		List<Integer> percents = service.getBigTaskPercentBySSId(student.getId(), courseId, bigTasks);

		map.put("tasks", tasks);
		map.put("bigTasks", bigTasks);
		map.put("percents", percents);
		map.put("courseId", courseId);
		map.put("size", tasks.size());

		return "student/course";
	}

	@GetMapping("student/course/score")
	@ResponseBody
	public String score(@RequestParam("courseId") Integer courseId,Integer page,Integer limit) {
		
		SmallTaskService service = new SmallTaskService();
		
		Student student = (Student) SecurityUtils.getSubject().getPrincipal();


		// List<SmallTask> tasks = service.getSmallTaskByCASId(courseId, student.getId());
		List<SmallTask> tasks = service.getBeforeNum(courseId, student.getId());
		// List<SmallTask> tasksNow = new ArrayList<SmallTask>();
		Integer size=tasks.size();

		List<SmallTask> tasksNow = service.getAfter(courseId, student.getId());
		tasks = service.getBefore(courseId, student.getId(), page, limit);

		
		service.autoSetSmallTaskScore(student.getId(), tasks);
		service.autoSetSmallTaskScore(student.getId(), tasksNow);

		
		JSONObject object = new JSONObject();
		object.put("code", 0);
		object.put("msg", "");
		object.put("count", size);
		object.put("tasks", tasks); 
		object.put("tasksNow", tasksNow); 
	
		return object.toJSONString();
	}


	@RequestMapping("/student/uploadTask")
	public String uploadTask(Integer courseId,Integer bigId,Integer smallId, Map<String, Object> map){
		SmallTaskService service = new SmallTaskService();

		Student student = (Student) SecurityUtils.getSubject().getPrincipal();
		if(!service.updateTask(student.getId(), courseId, bigId, smallId, -1f)){
			service.InsertTask(student.getId(), courseId, bigId, smallId, -1f);
		}



		BigTaskService bigService = new BigTaskService();

		// List<Course> courses = student.getCourses();
		// 还没改 已改

		List<SmallTask> tasks = service.getSmallTaskByCASId(courseId, student.getId());
		service.autoSetSmallTaskScore(student.getId(), tasks);
		List<BigTask> bigTasks = bigService.getBigTaskByCASId(courseId, student.getId());
		List<Integer> percents = bigService.getBigTaskPercentBySSId(student.getId(), courseId, bigTasks);

		map.put("tasks", tasks);
		map.put("bigTasks", bigTasks);
		map.put("percents", percents);
		map.put("courseId", courseId);
		map.put("size", tasks.size());

		return "student/course";
	}

}
