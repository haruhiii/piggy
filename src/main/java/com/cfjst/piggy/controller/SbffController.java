package com.cfjst.piggy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 本界面归黑福福所有 
 */
//huangfuuf zhuangyong   
@Controller
public class SbffController {
	@RequestMapping("/sbff")
	public String test() {
		return "test_hff/sbff";
    }

}
