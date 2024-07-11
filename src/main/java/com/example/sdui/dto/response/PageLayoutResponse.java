package com.example.sdui.dto.response;

import com.example.sdui.domain.PageLayout;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@Getter
@Builder
public class PageLayoutResponse {

	private final Long id;
	private final String name;
	private final String contents;

	public static PageLayoutResponse from(final PageLayout pageLayout) {
		return PageLayoutResponse.builder()
			.id(pageLayout.getId())
			.name(pageLayout.getName())
			.contents(pageLayout.getContents())
			.build();
	}
}
