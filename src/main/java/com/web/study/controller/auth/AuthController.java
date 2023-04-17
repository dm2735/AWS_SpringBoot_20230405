package com.web.study.controller.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.web.study.dto.DataResponseDto;
import com.web.study.dto.ResponseDto;
import com.web.study.dto.request.auth.LoginReqDto;
import com.web.study.dto.request.auth.RegistUserReqDto;
import com.web.study.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController {
	
	private final AuthService authService;
	
	@PostMapping("/auth/register")
	public ResponseEntity<? extends ResponseDto> regist (@RequestBody RegistUserReqDto registUserReqDto) {
		authService.duplicatedUsername(registUserReqDto);
		authService.registUser(registUserReqDto);
		return ResponseEntity.ok().body(ResponseDto.ofdefault());
	}
	
	@PostMapping("/auth/login")
	public ResponseEntity<? extends ResponseDto> login(@RequestBody LoginReqDto loginReqDto){
		
		authService.login(loginReqDto);
		
		return ResponseEntity.ok().body(DataResponseDto.of(authService.login(loginReqDto))); 
	}
}
