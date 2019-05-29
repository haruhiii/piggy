package com.cfjst.piggy.bean;

import java.io.Serializable;
import java.util.List;

import com.cfjst.piggy.service.CourseService;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Student extends User {

	/**
	 * 这里需要一丢丢注释
	 * 
	 */

	private Long id;
	private String name;
    private String password;
	private int sex;
	private Integer clazzId;	
	private List<Course> courses;



	public Student(Long id, String password, String name,Integer clazzId ){
		this.id = id;
		this.password = password;
		this.name = name;
		this.clazzId = clazzId;
	}
	
	public Student() {
	}

	public  List<Course> getCourses() {
        CourseService service = new CourseService();
        List<Course> courses = service.findByStudentId(this.id);
		return courses;
	}	
	public void setCourses( List<Course> courses) {
		
		this.courses=courses;
	}
}
