package com.web.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.study.IocAndDi.IocTest;
import com.web.study.IocAndDi.IocTest2;
import com.web.study.IocAndDi.TestA;


// @SpringBootApplication ==> @Component 탐색 => @Autowired  
@RestController
public class IocTestController {
	
	@Autowired
	  private IocTest iocTest ;
	@Autowired
	  private IocTest2 iocTest2;
	  
	 @GetMapping("/ioc/test")
		public Object test() {
		iocTest.run();
		iocTest2.run();
		return null;		
	
//	 @Autowired를 붙인 위의 형태는 아래의 형태와 같다.
	 
//		  private final IocTest iocTest ;
//		  private final IocTest2 iocTest2;
//		  
//		  public IocTestController(IocTest iocTest, IocTest2 iocTest2){
//		  	this.iocTest = iocTest;
//		  	this.iocTest2 = iocTest2;
//		 }
//		 
//		 @GetMapping("/ioc/test")
//			public Object test() {
//			iocTest.run();
//			iocTest2.run();
//			return null;
	}
}
