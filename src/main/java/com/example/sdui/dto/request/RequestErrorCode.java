package com.example.sdui.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class RequestErrorCode {

	private static final String DELIMITER = ",";

	static final String BLANK = "INPUT_001" + DELIMITER + "입력값이 없습니다.";
	static final String INVALID_JSON_FORMAT = "INPUT_002" + DELIMITER + "JSON 형식이 올바르지 않습니다.";

	public static String[] splitCodeAndMessage(final String error) {
		System.out.println(error);
		return error.split(DELIMITER);
	}
}
