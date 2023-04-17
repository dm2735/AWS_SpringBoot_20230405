package com.web.study.service;

import com.web.study.dto.request.auth.LoginReqDto;
import com.web.study.dto.request.auth.RegistUserReqDto;
import com.web.study.dto.response.auth.JwtTokenRespDto;

public interface AuthService {
	
	public void registUser(RegistUserReqDto registUserReqDto);
	public void duplicatedUsername(RegistUserReqDto registUserReqDto);
	
	public JwtTokenRespDto login(LoginReqDto loginReqDto);
}
