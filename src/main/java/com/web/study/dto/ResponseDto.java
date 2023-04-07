package com.web.study.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class ResponseDto {
	private final boolean success;
	private final int code;
	private final String message;
	
	public static ResponseDto ofdefault() {
		return new ResponseDto(true, 200, "Successfully");
	}
	
	public static ResponseDto of(boolean success, int code, String message) {
		return new ResponseDto(success, code, message);
	}
}
