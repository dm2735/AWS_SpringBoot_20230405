package com.web.study.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.study.dto.DataResponseDto;
import com.web.study.dto.ResponseDto;
import com.web.study.dto.request.BasicTestDto2;

import lombok.Data;

@Data

@RestController
public class BasicRestController2 {
	
	@GetMapping("/myname")
	public ResponseEntity<? extends ResponseDto> param(
			@RequestParam("name") String myName){
		
		return ResponseEntity.ok().body(DataResponseDto.of(myName));
	}
	
	@GetMapping("/introduce")
	public ResponseEntity<? extends ResponseDto> params(
			String email,String phone){
		
			String userInfo = email + "("+ phone +")";
		
		return ResponseEntity.ok().body(DataResponseDto.of(userInfo));
	}
	
	@GetMapping("/introduce2")
	public ResponseEntity<? extends ResponseDto> param(BasicTestDto2 basicTestDto2
			){
			String userInfo2 = "이름:" + basicTestDto2.getName() +"\n"
							+ "나이:" + basicTestDto2.getAge()	+"\n"
							+ "email:" + basicTestDto2.getEmail()	+"\n"
							+ "phone:" + basicTestDto2.getPhone()	+"\n";
			
			return ResponseEntity.ok().body(DataResponseDto.of(userInfo2));
	}
	
	@PostMapping("/posttest")                           // formData 는 @RequestBody 을 하지 않고 한다.
	public ResponseEntity<? extends ResponseDto> params2(BasicTestDto2 basicTestDto2){
		
		return ResponseEntity.created(null).body(DataResponseDto.of(basicTestDto2));
	}
	
	@PostMapping("/posttest2") 
	public ResponseEntity<? extends ResponseDto> params3 ( @RequestBody BasicTestDto2 basicTestDto2){
		
		return ResponseEntity.created(null).body(DataResponseDto.of(basicTestDto2));
	}
	
}
