package com.example.sdui.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SduiRequest {

	@NotBlank(message = RequestErrorCode.BLANK)
	@JsonFormat
	private String json;
}
