package com.web.study.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class LectureRespDto {
	private int id;
	private String lectureName;
	private int lecturePrice;
	private int instructorId;
	private String instructorName;
}
