package com.zj.springmvc.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

@Service
public class PushService {
	
	private DeferredResult<String> deferredResult;
	
	//在PushService里产生DeferredResult给控制器使用
	public DeferredResult<String> getAsyncUpdate(){
		deferredResult=new DeferredResult<>();
		return deferredResult;
	}
	
	@Scheduled(fixedDelay=5000)  //通过refresh方法定时更新DeferredResult
	public void refresh(){ //
		if(deferredResult!=null){
			deferredResult.setResult(new Long(System.currentTimeMillis()).toString());
		}
	}
}
