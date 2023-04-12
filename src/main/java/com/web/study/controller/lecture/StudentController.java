package com.web.study.controller.lecture;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.web.study.dto.DataResponseDto;
import com.web.study.dto.ResponseDto;
import com.web.study.dto.request.student.StudentReqDto;
import com.web.study.service.StudentService;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
public class StudentController {
	
	private final StudentService studentService;
	
	@PostMapping("/student")
	public ResponseEntity<? extends ResponseDto> registStudent(@RequestBody StudentReqDto studentReqDto){
		studentService.registStudent(studentReqDto);
		return ResponseEntity.ok().body(ResponseDto.ofdefault());
	}
	
	@GetMapping("/students")
	public ResponseEntity<? extends ResponseDto> getStudentAll(){
		
		return ResponseEntity.ok().body(DataResponseDto.of(studentService.getStudentAll()));
	}
	
	@GetMapping("/student/{id}")
	public ResponseEntity<? extends ResponseDto> getStudentById(@PathVariable int id){
		
		return ResponseEntity.ok().body(DataResponseDto.of(studentService.getStudentById(id)));
	}
		
}


//Post 요청을 보내면 JSON형식으로 데이터가 들어온다.
//JSON으로 들어온 데이터를 @RequestBody를 사용해서 String으로 변환하여 받아서 ReqDto에 담는다.
//담은 객체를 Service로 보낸다.
