package com.example.sdui.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.sdui.dto.request.RequestErrorCode;
import com.example.sdui.dto.response.SduiResponse;
import com.example.sdui.exception.SduiException;

@RestControllerAdvice
public class ControllerAdvice {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<SduiResponse<Void>> handleInvalidRequest(final BindingResult bindingResult) {
		final List<FieldError> fieldErrors = bindingResult.getFieldErrors();
		final FieldError mainError = fieldErrors.get(0);
		String[] splitError = RequestErrorCode.splitCodeAndMessage(
			Objects.requireNonNull(mainError.getDefaultMessage()));

		SduiResponse<Void> response = SduiResponse.fail(splitError[0], splitError[1]);
		return ResponseEntity.badRequest().body(response);
	}

	@ExceptionHandler(SduiException.class)
	public ResponseEntity<SduiResponse<Void>> handleIllegalArgumentException(final SduiException exception) {
		SduiResponse<Void> response = SduiResponse.fail(exception.getErrorCode(), exception.getMessage());
		return ResponseEntity.status(exception.getStatusCode()).body(response);
	}
}
