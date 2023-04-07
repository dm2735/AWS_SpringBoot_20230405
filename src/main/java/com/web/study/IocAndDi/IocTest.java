package com.web.study.IocAndDi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class IocTest {
	
	@Qualifier("t1")
	@Autowired
	private Test test;
	
//	public IocTest(Test test) {
//		this.test = test;
//	}
	
	public void run() {
		test.PrintTest();
		System.out.println("IOCTEST 출력");
	}
}
