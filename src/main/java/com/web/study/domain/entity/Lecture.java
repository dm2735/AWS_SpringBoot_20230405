package com.web.study.domain.entity;

import java.time.LocalDate;

import com.web.study.dto.response.LectureRespDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Lecture {
	private int ltm_id;
	private String ltm_name;
	private int ltm_price;
	private int itm_id;
	private Instructor instructor;
	
	public LectureRespDto todo() {
		String instructorName=null;
		itm_id = 0;
		if(instructor != null) {
			instructorName = instructor.getItm_name();
			itm_id = instructor.getItm_id();
		}
		
		return LectureRespDto.builder()
				.id(ltm_id)
				.lectureName(ltm_name)
				.lecturePrice(ltm_price)
				.instructorId(itm_id)
				.instructorName(instructorName)
				.build();
	}
}
