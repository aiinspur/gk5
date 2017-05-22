package com.tigerj.jinjingzheng;

import org.apache.http.HttpHost;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;


@RunWith(SpringRunner.class)
@SpringBootTest
public class JjzTest {
	
	//https://api.jinjingzheng.zhongchebaolian.com/enterbj/platform/enterbj/entercarlist
	@Autowired
	private RestTemplate restTemplate;
	
//	public JjzTest(RestTemplateBuilder restTemplateBuilder) {
//		restTemplate = restTemplateBuilder.build();
//	}
	
	@Test
	public void info(){
		System.out.println("restTemplate:"+restTemplate.toString());
		restTemplate.getForEntity("https://www.baidu.com", String.class);
		
	}

}
