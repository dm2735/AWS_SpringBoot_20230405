package com.web.study.service;

import java.util.List;

import com.web.study.dto.request.course.CourseReqDto;
import com.web.study.dto.response.CourseRespDto;

public interface CourseService {
	
	public void registLectureRegist(CourseReqDto lectureRegistReqDto);
	public List<CourseRespDto> getCourseAll();
	public List<CourseRespDto> searhCourse(int type, String searchValue);

}