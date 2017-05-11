package com.tigerj.restTemplate;

import org.springframework.util.Assert;

public class Test {
	
	public static void main(String[] args) {
		String s = null;
		Assert.notNull(s,"s not null.");
		
		System.out.println(s);
	}
	
	//@org.junit.Test
	public void test(){
		String s = null;
		Assert.notNull(s,"s not null.");
		
	}

}
