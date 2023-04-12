package com.web.study.dto.request.course;

import java.time.LocalDate;

import com.web.study.domain.entity.Course;

import lombok.Setter;
import lombok.ToString;

@Setter
@ToString
public class CourseReqDto {
	private int lectureId;
	private int studentId;
	private LocalDate registDate;
	
	public Course toEntity() {
		
		return Course.builder()
				.ltm_id(lectureId)
				.sdm_id(studentId)
				.regist_date(registDate)
				.build();
	}
}
