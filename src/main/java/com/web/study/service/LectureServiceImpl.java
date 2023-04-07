package com.web.study.service;

import org.springframework.stereotype.Service;

import com.web.study.domain.entity.Lecture;
import com.web.study.dto.request.lecture.LectureReqDto;
import com.web.study.repository.LectureRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class LectureServiceImpl implements LectureService {
	
	private final LectureRepository lectureRepository;
	
	@Override
	public void registLecture(LectureReqDto lectureReqDto) {
		//DTO -> Entity 변환
		Lecture lecture = lectureReqDto.toEntity();
		System.out.println("변환:" + lecture);
		lectureRepository.regist(lecture);
	
	}

}
