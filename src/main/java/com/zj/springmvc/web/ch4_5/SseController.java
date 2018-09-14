package com.zj.springmvc.web.ch4_5;

import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SseController {
	
	//这里输出的媒体类型为text/event-stream，这是服务器端SSE的支持
	@RequestMapping(value="/push",produces="text/event-stream")
	public @ResponseBody String push(){
		Random r=new Random();
		try{
			Thread.sleep(5000);//每五秒钟向浏览器推送随机消息
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		return "data:Testing 1,2,3"+r.nextInt()+"\n\n";
	}
}
