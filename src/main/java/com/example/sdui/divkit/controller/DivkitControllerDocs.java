package com.example.sdui.divkit.controller;

import org.springframework.http.ResponseEntity;

import com.example.sdui.common.dto.response.SduiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "divkit")
public interface DivkitControllerDocs {

	@Operation(summary = "divkit 샘플 조회 (현재 1, 2만 있습니다.)")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "divkit 샘플 조회 성공"),
		@ApiResponse(responseCode = "400", description = "입력 형식이 잘못됨", content = @Content(
			examples = {
				@ExampleObject(name = "해당 id의 divkit 샘플이 존재하지 않음", value = """
					{
					  "success": false,
					  "error": {
					    "code": "SAMPLE_001",
					    "message": "해당 id의 Sample이 없습니다."
					  },
					  "data": null
					}
					"""),
			}))
	})
	ResponseEntity<SduiResponse<Object>> findSample(int id);
}
