package com.cfjst.piggy.controller;


import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cfjst.piggy.bean.CTC;
import com.cfjst.piggy.bean.Course;
import com.cfjst.piggy.service.CourseService;

@Controller
@RequestMapping("course")
public class CourseController {

	 @Resource
	 private CourseService courseService;
	 
	 @RequestMapping("findAllCourse")
	 public String findAll(Model model) throws IOException {
			
			List<CTC> course = courseService.findAllCourse();
			model.addAttribute("course",course);
			return "admin/course";
	}
	 
	 @RequestMapping("toAddCourse")	
	 public String toAdd() {
			
			return "admin/add_course";
	}
	 
	 @PostMapping("addCourse")
	 public String add(@ModelAttribute CTC ctc) throws IOException  {
		 courseService.addCTC(ctc);
		 Course course = new Course();
		 course.setId(ctc.getCourse().getId());
		 course.setName(ctc.getCourse().getName());
		 courseService.addCourse(course);
		 return "success";
	 }
	 
	 
	 @RequestMapping("toUpdateCourse")	
	 public String toUpdate(int id, Model model) throws IOException {
		 CTC ctc = courseService.findByCTCId(id);
		 
		 model.addAttribute("ctc",ctc);
		 return "admin/update_course";
	}
	 
	 @PostMapping("updateCourse")
	 public String updateCourse(@ModelAttribute CTC ctc) throws IOException  {
		
		courseService.updateCTC(ctc);
		Course course = new Course();
		course.setId(ctc.getCourse().getId());
		course.setName(ctc.getCourse().getName());
		courseService.updateCourse(course);
		return "success";
	 }
	 @RequestMapping("deleteCourse")
	 public String deleteCourse(@RequestParam(value = "id") int id, Model model) throws IOException {
		 courseService.deleteCourse(id);
		 List<CTC> course = courseService.findAllCourse();
		 model.addAttribute("course",course);
		 return "admin/course";
	 }
	 
	 
}
