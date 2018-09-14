package com.zj.springmvc.web.ch4_5;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zj.springmvc.domain.DemoObj;

@Controller
public class ConverterController {
	
	//指定返回的媒体类型为自定义的媒体类型application/x-wisely
	@RequestMapping(value="/convert",produces={"application/x-wisely"})
	public @ResponseBody DemoObj convert(@RequestBody DemoObj demoObj){
		return demoObj;
	}
}
