package com.example.sdui.dto.validation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class JsonFormatValidator implements ConstraintValidator<JsonFormat, String> {

	private final ObjectMapper objectMapper;

	public JsonFormatValidator() {
		this.objectMapper = new ObjectMapper();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		try {
			objectMapper.readTree(value);
			return true;
		} catch (JsonProcessingException e) {
			return false;
		}
	}
}
