package com.zj.springmvc.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller  //声明是一个控制器
public class HelloController {
	
	@RequestMapping("/index")  //配置URL和方法之间的映射
	public String hello(){
		return "index";//说明页面放置的路径为/WEB-INF/classes/views/index.jsp
	}
}
