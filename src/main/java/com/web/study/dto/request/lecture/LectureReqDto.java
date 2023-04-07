package com.web.study.dto.request.lecture;

import com.web.study.domain.entity.Lecture;

import lombok.Setter;
import lombok.ToString;

@Setter
@ToString
public class LectureReqDto {
	private String lectureName;
	private int lecturePrice;
	private int lectureId;
	
	public Lecture toEntity() {
		
		return Lecture.builder()
				.lecture_name(lectureName)
				.lecture_price(lecturePrice)
				.lecturer_id(lectureId)
				.build();
	}
}
