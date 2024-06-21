package com.example.sdui.dto.request;

import com.example.sdui.dto.validation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SduiRequest {

	@NotBlank(message = RequestErrorCode.BLANK)
	@JsonFormat(message = RequestErrorCode.INVALID_JSON_FORMAT)
	private String json;
}
