package com.cfjst.piggy.bean;

import java.io.Serializable;
import java.util.HashMap;

import lombok.ToString;


// TODO 删除
@ToString
public class Tasks implements Serializable{

	private static final long serialVersionUID = -3582894678215843421L;
	/**
	 * 这里需要一丢丢注释 考核项
	 */

	//大项ID,名称
	private HashMap<Integer,String>  bigTaskName;
	//大项id，大项比例
	private HashMap<Integer,Float>  bigTaskRate;
	 
	//小项ID，父项ID
	private HashMap<Integer,Integer> smallTaskId;
	//小项ID，小项名
	private HashMap<Integer,String> smallTaskName; 
	//小项ID，小项比例
	private HashMap<Integer,Float> smallTaskRate; 
	//小项ID，小项分数
	private HashMap<Integer,Float> smallTaskScore; 
 

	//id，名字，比率
	//
	/**
	 * 添加一个大项
	 * @param name 大项名字
	 * @param id   id
	 * @param rate 比率
	 */
	public void addBigTask(String name,Integer id,Float rate) {
		bigTaskName.put(id,name);
		bigTaskRate.put(id,rate);
	}

	
	//父项id，名字，比率
	//
	/**
	 * 添加一个小项
	 * @param superId 父项id
	 * @param id 	  id
	 * @param name	  名字
	 * @param rate	  比率
	 */
	public void addsmallTask(Integer superId,Integer id,String name,Float rate) {
		smallTaskId.put(superId,id);
		smallTaskName.put(id,name);
		smallTaskRate.put(id, rate);
	}
	/**
	 * 添加设置小项分数
	 * @param id	小项id
	 * @param score 分数
	 */
	public void setSmallTaskScore(Integer id, Float score){
		smallTaskScore.put(id, score);
	}



}
