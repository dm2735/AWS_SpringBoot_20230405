package com.web.study.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.web.study.domain.entity.Student;
import com.web.study.dto.request.student.StudentReqDto;
import com.web.study.dto.response.StudentRespDto;
import com.web.study.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class StudentServiceImpl implements StudentService{
	
	private final StudentRepository studentRepository;
	@Override
	public void registStudent(StudentReqDto studentReqDto) {
		Student student = studentReqDto.toEntity();
		studentRepository.saveStudent(student);
	}
	
	@Override
	public List<StudentRespDto> getStudentAll() {
		List<StudentRespDto> dtos = new ArrayList<>();
		
		studentRepository.getStudentAll().forEach(entity -> {
			dtos.add(entity.todo());
		});
		
		return dtos;
	}

	@Override
	public StudentRespDto getStudentById(int id) {

		return studentRepository.getStudentById(id).todo();
	}

	
}

//Service 인터페이스는 ReqDto를 가지고 있고, Service를 임플리먼츠한 ServiceImpl은 ReqDto를 꺼내올 수 있다.
//Service