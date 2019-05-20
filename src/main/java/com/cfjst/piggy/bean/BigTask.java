package com.cfjst.piggy.bean;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


// TODO 删除
@ToString
@Setter
@Getter
public class BigTask {

	private String name;
	private Float rate;
	private Integer id;
	//完成百分比
    private Integer percent;

}
