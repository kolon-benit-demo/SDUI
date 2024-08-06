package com.example.sdui.pagelayout.dto.request;

import com.example.sdui.pagelayout.domain.PageLayout;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PageLayoutUpdatingRequest {

	private String name;
	@JsonFormat
	private String contents;

	public PageLayout toDomain() {
		return PageLayout.builder()
			.name(name)
			.contents(contents)
			.build();
	}
}
