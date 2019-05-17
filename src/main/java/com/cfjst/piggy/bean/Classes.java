package com.cfjst.piggy.bean;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Classes {

	private int Class_id;
	private String Class_name;
	private List<Course> course;

}
