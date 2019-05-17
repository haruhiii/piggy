package com.cfjst.piggy.bean;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class Course {

	/**
	 * 这里需要一丢丢注释
	 */

	private Integer id;
	private String name;
	private Integer grade;
	private Teacher teacher;
	private Clazz clazz;
	

}
