package com.cfjst.piggy.bean;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


// TODO 删除
@ToString
@Setter
@Getter
public class SmallTask {

	private String smallTaskName;
	private String bigTaskName;
	private Integer bigTaskId;
	private Date deadline;
	private String status;
    private Float score;
    


}