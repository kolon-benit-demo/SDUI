package com.example.sdui.divkit.exception;

import com.example.sdui.common.exception.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum DivkitErrorCode implements ErrorCode {

	SAMPLE_NOT_FOUND(400, "SAMPLE_001", "해당 id의 Sample이 없습니다."),
	;

	private final int statusCode;
	private final String errorCode;
	private final String message;
}
