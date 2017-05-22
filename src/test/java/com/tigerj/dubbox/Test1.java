package com.tigerj.dubbox;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tigerj.service.HelloService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test1 {
	
	@Autowired
	private HelloService helloService;
	
	@Test
	public void test(){
		helloService.sayHello();
	}

}
