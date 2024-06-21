package com.example.sdui.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class SduiException extends RuntimeException {

	private final int statusCode;
	private final String errorCode;
	private final String message;

	public SduiException(final ErrorCode code) {
		statusCode = code.getStatusCode();
		errorCode = code.getErrorCode();
		message = code.getMessage();
	}
}
