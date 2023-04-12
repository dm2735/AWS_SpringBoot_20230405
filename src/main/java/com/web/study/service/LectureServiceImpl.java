package com.web.study.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.web.study.domain.entity.Lecture;
import com.web.study.dto.request.lecture.LectureReqDto;
import com.web.study.dto.response.LectureRespDto;
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

	@Override
	public List<LectureRespDto> getLectureAll() {
		List<LectureRespDto> dtos = new ArrayList<>();
		
		lectureRepository.getLectureAll().forEach(entity -> {
			dtos.add(entity.todo());
		});
		return dtos;
	}

	@Override
	public LectureRespDto getLectureById(int id) {
		return lectureRepository.getLectureById(id).todo();
	}

	@Override
	public List<LectureRespDto> searchLecture(int type, String searchValue) {
		Map<String, Object> parameterMap = new HashMap<>();
		parameterMap.put("type", type);
		parameterMap.put("searchValue", searchValue);
		
		List<LectureRespDto> dtos = new ArrayList<>();
		lectureRepository.searchLecture(parameterMap).forEach(entity -> {
			dtos.add(entity.todo());
		});
		
		return dtos;
	}

}
