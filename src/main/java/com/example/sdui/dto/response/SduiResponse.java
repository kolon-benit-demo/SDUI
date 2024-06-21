package com.example.sdui.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class SduiResponse<T> {

	private final boolean success;
	private final ErrorResponse error;
	private final T data;

	public static <T> SduiResponse<T> success(T data) {
		return new SduiResponse<>(true, null, data);
	}

	public static <T> SduiResponse<T> fail(String errorCode, String message) {
		ErrorResponse error = new ErrorResponse(errorCode, message);
		return new SduiResponse<>(false, error, null);
	}
}
