package com.zj.springmvc;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.zj.springmvc.interceptor.DemoInterceptor;
import com.zj.springmvc.messageconverter.MyMessageConverter;

@Configuration
@EnableWebMvc // 会开启一些默认配置,若无此注解，则重写WebMvcConfigurerAdapter方法无效
@EnableScheduling
@ComponentScan("com.zj.springmvc")
public class MyMvcConfig extends WebMvcConfigurerAdapter {// 继承WebMvcConfigurerAdapter类，重写其方法可对Spring
															// MVC进行配置
	@Bean
	public InternalResourceViewResolver viewResolver() {
		// 用来映射路径和实际页面的位置
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/classes/views/");
		viewResolver.setSuffix(".jsp");
		viewResolver.setViewClass(JstlView.class);
		return viewResolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// addResourceHandlers指的是文件放置的目录，addResourceHandler指的是对外暴露的访问路径
		registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/assets/");
	}

	@Bean // 配置拦截器的Bean
	public DemoInterceptor demoInterceptor() {
		return new DemoInterceptor();
	}

	@Override
	// 重写addInterceptors方法，注册拦截器
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(demoInterceptor());
	}

	@Override
	//简化页面转向配置
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/index").setViewName("/index");
		registry.addViewController("/toUpload").setViewName("/upload");
		registry.addViewController("/converter").setViewName("/converter");
		registry.addViewController("/sse").setViewName("/sse");
		registry.addViewController("/async").setViewName("/async");
	}
	
	@Override
	//通过重写configurePathMatch方法可不忽略"."后面的参数
	public void configurePathMatch(PathMatchConfigurer configurer) {
		configurer.setUseSuffixPatternMatch(false);
	}
	
	@Bean
	public MultipartResolver multipartResolver(){
		CommonsMultipartResolver multipartResolver=
				new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(1000000);
		return multipartResolver;
	}
	
	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(converter());
	}

	public MyMessageConverter converter() {
		return new MyMessageConverter();
	}
}
