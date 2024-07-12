package com.example.sdui.pagelayout.domain;

import com.example.sdui.common.exception.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum PageLayoutErrorCode implements ErrorCode {

	PAGE_LAYOUT_NOT_FOUND(400, "PAGE_LAYOUT_001", "해당 id의 PageLayout이 없습니다."),
	;

	private final int statusCode;
	private final String errorCode;
	private final String message;
}
