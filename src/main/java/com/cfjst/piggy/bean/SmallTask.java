package com.cfjst.piggy.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


// TODO 删除
@ToString
@Setter
@Getter
public class SmallTask {

	private Integer id;
	private String smallTaskName;
	private Integer courseId;
	private String bigTaskName;
	private Integer bigTaskId;
	private Date deadline;
	private String status;
    private Float score;
	
	public String getDeadline(){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(deadline);
	}

	public Date getDeadlineDate(){
		return deadline;
	}
}
