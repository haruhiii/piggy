package com.cfjst.piggy.controller;


import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



/**
 * 通用请求
 */


@Controller
public class IndexController {

	@RequestMapping("/")
	public String index(Map<String,Object> map)  {
		map.put("msg", "Hello world!");
		return "index";
	}
	 
	@RequestMapping("/login")
	public String login()  {

		return "login";
	}
	
}
