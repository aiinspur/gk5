package com.tigerj.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.tigerj.service.HelloService;

@Service(interfaceClass=HelloService.class)
public class HelloServiceImpl implements HelloService{

	@Override
	public void sayHello() {
		System.out.println("Hello Tigerj.");
		
	}

}
