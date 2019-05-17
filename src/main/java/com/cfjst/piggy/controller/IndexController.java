package com.cfjst.piggy.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	 @RequestMapping("hello")
	 public String hello()  {
			return "hello";
	}
	 
}
