package com.tigerj.dubbox;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.AnnotationBean;

//@Configuration
public class DubboxConfig {

	@Value("${dubbox.application.name}")
	private String dubboxApplicationName;

	@Value("${dubbox.registry.address}")
	private String dubboxRegistryAddress;
	
	@Bean
	public ApplicationConfig applicationConfig(){
		ApplicationConfig applicationConfig = new ApplicationConfig();  
        applicationConfig.setName(dubboxApplicationName);  
        return applicationConfig;
	}
	
	@Bean
	public RegistryConfig registryConfig(){
		RegistryConfig registryConfig = new RegistryConfig();  
        registryConfig.setAddress(dubboxRegistryAddress);
        //registryConfig.setUsername("");
        //registryConfig.setPassword("");
        return registryConfig; 
	}
	
	@Bean
	public static AnnotationBean annotationBean(){
		AnnotationBean annotationBean = new AnnotationBean();  
        annotationBean.setPackage("com.tigerj.service,com.colorTiger.service");//多个包可使用英文逗号隔开  
        return annotationBean;  
	}
	
	public ProtocolConfig protocolConfig(){
		ProtocolConfig protocol = new ProtocolConfig();
		protocol.setName("dubbo");
		//protocol.setPort(12345);
		protocol.setThreads(200);
		return protocol;
	}

}
