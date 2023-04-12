package com.web.study.controller.lecture;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.web.study.dto.DataResponseDto;
import com.web.study.dto.ResponseDto;
import com.web.study.dto.request.course.CourseReqDto;
import com.web.study.service.CourseService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CourseController {
	
	private final CourseService courseService;
	
	//Creat

	@PostMapping("/course")
	public ResponseEntity<? extends ResponseDto> register(@RequestBody CourseReqDto courseReqDto){
		courseService.registLectureRegist(courseReqDto);
		
		return ResponseEntity.ok().body(ResponseDto.ofdefault());
	}
	
	//Read
	@GetMapping("/courses")
	public ResponseEntity<? extends ResponseDto> getCourseAll(){
		
		return ResponseEntity.ok().body(DataResponseDto.of(DataResponseDto.of(courseService.getCourseAll())));
	}
	
	@GetMapping("/search/courses")
	public ResponseEntity<? extends ResponseDto> searchCourse(int type, String searchValue){
		
		return ResponseEntity.ok().body(DataResponseDto.of(DataResponseDto.of(courseService.searhCourse(type, searchValue))));
	}
	
	//Updata
	public ResponseEntity<? extends ResponseDto> modify(){
		
		return ResponseEntity.ok().body(ResponseDto.ofdefault());
	}
	
	//Delete
	public ResponseEntity<? extends ResponseDto> remove(){
		
		return ResponseEntity.ok().body(ResponseDto.ofdefault());
	}
}
