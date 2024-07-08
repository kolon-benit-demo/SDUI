package com.example.sdui.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.sdui.dto.request.PageLayoutRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "PageLayout")
public interface PageLayoutControllerDocs {

	@Operation(summary = "PageLayout 저장")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "201", description = "PageLayout 저장 성공"),
		@ApiResponse(responseCode = "400", description = "입력 형식이 잘못됨", content = @Content(
			examples = {
				@ExampleObject(name = "입력값이 없음", value = """
					{
					    "success": false,
					    "error": {
					        "code": "INPUT_001",
					        "message": "입력값이 없습니다."
					    },
					    "data": null
					}
					"""),
			}))
	})
	ResponseEntity<Void> create(@RequestBody final PageLayoutRequest request);
}
