package com.web.study.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.study.dto.DataResponseDto;
import com.web.study.dto.ResponseDto;
import com.web.study.dto.request.BasicTestDto;

import lombok.Data;



@Data


@RestController
public class BasicRestController {
	
	// GET요청의 param을 처리하는 방법
	
	@GetMapping("/read")							// == RequestParam("age") int age
	
	public ResponseEntity< ? extends ResponseDto> read(BasicTestDto basicTestDto) {
			
		String userInfo = basicTestDto.getName() + "(" + basicTestDto.getAge() + ")";
		
		return ResponseEntity.ok().body(DataResponseDto.of(userInfo));
	}
	
	@GetMapping("/read2/{id}")
	public ResponseEntity<? extends ResponseDto> read2(@PathVariable("id") int id) {
		Map<Integer, String> userMap = new HashMap<>();
		
		userMap.put(1, "김동민");
		userMap.put(2, "김동면");
		userMap.put(3, "김동만");
		userMap.put(4, "김동맨");
		userMap.put(5, "김동밍");
		userMap.put(6, "김동명");
		
		return ResponseEntity.ok().body(DataResponseDto.of(userMap.get(id)));
	}
	
	//포스트요청의 데이터 처리
	@PostMapping("/create")
	public ResponseEntity<? extends ResponseDto> create(@RequestBody BasicTestDto basicTestDto) {
		System.out.println("데이터 추가");
		
		return ResponseEntity.created(null).body(DataResponseDto.of(basicTestDto));
	}
}
