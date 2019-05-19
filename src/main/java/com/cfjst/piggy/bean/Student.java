package com.cfjst.piggy.bean;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Student {

	/**
	 * 这里需要一丢丢注释
	 * 
	 */

	private Long id;
	private String name;
    private String password;
	private int sex;
	private Integer clazzId;	

	public Student(Long id, String password, String name,Integer clazzId ){
		this.id = id;
		this.password = password;
		this.name = name;
		this.clazzId = clazzId;
	}
	
	public Student() {
	}

}
