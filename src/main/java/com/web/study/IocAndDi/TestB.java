package com.web.study.IocAndDi;

import org.springframework.stereotype.Component;

@Component("t2")
public class TestB  implements Test{
	
	@Override
	public void PrintTest() {
		System.out.println("TestB 클래스!!!");		
	}
}
