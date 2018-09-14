package com.zj.springmvc.web.ch4_6;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.zj.springmvc.MyMvcConfig;
import com.zj.springmvc.service.DemoService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={MyMvcConfig.class})
@WebAppConfiguration("src/main/resources") //用来声明加载的ApplicationContext是一个WebApplicationContext，它的属性指定的是Web资源的位置
public class TestControllerIntegrationTests {
	private MockMvc mockMvc; //MockMvc模拟MVC对象
	
	@Autowired
	private DemoService demoService; //可以在测试用例中注入Spring的Bean
	@Autowired
	WebApplicationContext wac; //可注入WebApplicationContext
	@Autowired
	MockHttpSession session; //可注入模拟的http session，此处仅作演示，没有shiyong
	@Autowired
	MockHttpServletRequest request;//可注入模拟的http request，....
	
	@Before //在测试开始前进行的初始化工作
	public void setup(){
		//初始化MockMvc对象
		this.mockMvc=MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	
	@Test
	public void testNormalController() throws Exception{
		mockMvc.perform(get("/normal"))  //模拟向/normal进行get请求
		.andExpect(status().isOk())
		.andExpect(view().name("page"))
		.andExpect(forwardedUrl("/WEB_INF/classes/views/page.jsp"))
		.andExpect(model().attribute("msg",demoService.saySomething()));
	}
	
	@Test
	public void testRestController() throws Exception{
		mockMvc.perform(get("/testRest"))
		.andExpect(status().isOk())
		.andExpect(content().contentType("text/plain;charset=UTF-8"))
		.andExpect(content().string(demoService.saySomething()));
	}
	
}
