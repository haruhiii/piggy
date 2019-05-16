package com.cfjst.piggy.bean;

import java.util.List;

public class Classes {

	private int Class_id;
	private String Class_name;
	private List<Course> course;
	public List<Course> getCourse() {
		return course;
	}
	public void setCourse(List<Course> course) {
		this.course = course;
	}
	public int getClass_id() {
		return Class_id;
	}
	public void setClass_id(int class_id) {
		this.Class_id = class_id;
	}
	public String getClass_name() {
		return Class_name;
	}
	public void setClass_name(String class_name) {
		this.Class_name = class_name;
	}
	
	
}
