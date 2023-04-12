package com.web.study.controller.lecture;

import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.study.domain.entity.Course;
import com.web.study.dto.DataResponseDto;
import com.web.study.dto.ResponseDto;
import com.web.study.dto.request.course.CourseReqDto;
import com.web.study.dto.request.lecture.LectureReqDto;
import com.web.study.dto.request.instructor.InstructorReqDto;
import com.web.study.dto.request.student.StudentReqDto;
import com.web.study.service.CourseService;
import com.web.study.service.LectureService;
import com.web.study.service.InstructorService;
import com.web.study.service.StudentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LectureController {
	
	private final LectureService lectureService;

	
	//Creat
	@PostMapping("/lecture")
	public ResponseEntity<? extends ResponseDto> register(@RequestBody LectureReqDto lectureReqDto){
		lectureService.registLecture(lectureReqDto);
		
		return ResponseEntity.ok().body(ResponseDto.ofdefault());
	}
	
	//Read
	@GetMapping("/lectures")
	public ResponseEntity<? extends ResponseDto> getLectureAll(){
		
		return ResponseEntity.ok().body(DataResponseDto.of(lectureService.getLectureAll()));
	}
	
	@GetMapping("/lecture/{id}")
	public ResponseEntity<? extends ResponseDto> getLectureById(@PathVariable int id){
		
		return ResponseEntity.ok().body(DataResponseDto.of(lectureService.getLectureById(id)));
	}
	
	@GetMapping("/search/lecture")
	public ResponseEntity<? extends ResponseDto> searchLecture(int type, String searchValue){
		
		return ResponseEntity.ok().body(DataResponseDto.of(DataResponseDto.of(lectureService.searchLecture(type, searchValue))));
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
