package com.cfjst.piggy.bean;

import java.util.List;

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

	private Integer id;
	private String name;

	// TODO 存疑
	private Long clazzId;
	private List<Integer> bigTaskPercent;
	

	private Long teacherId;

	private String worningType;
	private String worningText;


}
