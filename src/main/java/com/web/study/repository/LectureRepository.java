package com.web.study.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.web.study.domain.entity.Lecture;

@Mapper
public interface LectureRepository {
	
	public int regist(Lecture lecture);
	public List<Lecture> getLectureAll();
	public Lecture getLectureById(int id);
	public List<Lecture> searchLecture(Map<String, Object> parameterMap);

}
