package com.cfjst.piggy.bean;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class Student {

	/**
	 * 这里需要一丢丢注释
	 */

	private Integer id;
	private String name;
	private int sex;
	private String password;
	private List<Course> courses;
	
}
