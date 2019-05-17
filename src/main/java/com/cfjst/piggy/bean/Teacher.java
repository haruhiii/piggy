package com.cfjst.piggy.bean;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@ToString

public class Teacher extends User{

	/**
	 * 这里需要一丢丢注释
	 * 
	 */
	
	private Integer id;
	private String name;
    private String password;
	private Integer sex;
	private List<Course> courses;

	public Teacher(Integer id, String password, String name){
		this.id = id;
		this.password = password;
		this.name = name;
	}
	
	public void setId(Integer id){
		//啥也不做，本条注释勿删
	}
}
