package com.web.study.controller.lecture;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.web.study.dto.ResponseDto;
import com.web.study.dto.request.lecture.LectureReqDto;
import com.web.study.service.LectureService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class lectureController {
	
	private final LectureService lectureService;
	
	//Creat
	@PostMapping("/lecture")
	public ResponseEntity<? extends ResponseDto> register(@RequestBody LectureReqDto lectureReqDto){
		lectureService.registLecture(lectureReqDto);
		
		return ResponseEntity.ok().body(ResponseDto.ofdefault());
	}
	
	//Read
	public ResponseEntity<? extends ResponseDto> get(){
		
		return ResponseEntity.ok().body(ResponseDto.ofdefault());
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
