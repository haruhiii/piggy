package com.cfjst.piggy.bean;

import java.util.List;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class Teacher {

	/**
	 * 这里需要一丢丢注释
	 */
	private Integer id;
	private String name;
	private Integer sex;
	private String password;
	private List<Course> courses;

	
}
