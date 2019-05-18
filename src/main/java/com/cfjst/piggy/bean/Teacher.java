package com.cfjst.piggy.bean;



import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Teacher {

	/**
	 * 这里需要一丢丢注释
	 * 教师
	 */

	private Long id;
	private String name;
	private String password;
	private int sex;
	private Course course;


	public Teacher() {
	}

	public Teacher(Long id, String password, String name) {
		this.id = id;
		this.password = password;
		this.name = name;
	}



	
}
