package com.cfjst.piggy.bean;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class Teacher {

	private int Teacher_id;
	private String Name;
	private int sex;
	private String password;
	private List<Course> course;

	
}
