package com.example.sdui.dto.request;

import com.example.sdui.domain.PageLayout;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PageLayoutUpdatingRequest {

	private String name;
	private String contents;

	public PageLayout toDomain() {
		return PageLayout.builder()
			.name(name)
			.contents(contents)
			.build();
	}
}
