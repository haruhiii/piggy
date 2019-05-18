package com.cfjst.piggy.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString

public class Course {

	/**
	 * 这里需要一丢丢注释
	 */

	private Long id;
	private String name;
	private Integer grade;
	private Teacher teacher;
	private Clazz clazz;
	

}
