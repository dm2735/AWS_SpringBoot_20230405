package com.web.study.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.web.study.dto.DataResponseDto;
import com.web.study.dto.ErrorResponseDto;
import com.web.study.dto.ResponseDto;

@RestController

public class BasicController {
	@GetMapping("/view/test")
	
	public ResponseEntity< ? extends ResponseDto> view() {
		List<String> strList = new ArrayList<>();
		strList.add("a");
		strList.add("b");
		strList.add("c");
		strList.add("d");
		strList.add("e");
		
		if(strList.contains("e")) {
			try {
				throw new RuntimeException("ㅅㅂ오류났어~");
			} catch (Exception e){
				return ResponseEntity.internalServerError().body(ErrorResponseDto.of(HttpStatus.BAD_REQUEST, e));
			}
		}
			
		
		return ResponseEntity.ok().body(DataResponseDto.of(strList));
	}
}
