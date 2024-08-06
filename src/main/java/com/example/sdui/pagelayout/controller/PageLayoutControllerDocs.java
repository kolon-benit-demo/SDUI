package com.example.sdui.pagelayout.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.sdui.common.dto.response.SduiResponse;
import com.example.sdui.pagelayout.dto.request.PageLayoutRequest;
import com.example.sdui.pagelayout.dto.request.PageLayoutUpdatingRequest;
import com.example.sdui.pagelayout.dto.response.PageLayoutResponse;

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

	@Operation(summary = "PageLayout 상세 조회")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "PageLayout 상세 조회 성공"),
		@ApiResponse(responseCode = "400", description = "입력 형식이 잘못됨", content = @Content(
			examples = {
				@ExampleObject(name = "해당 id의 PageLayout이 존재하지 않음", value = """
					{
					    "success": false,
					    "error": {
					        "code": "PAGE_LAYOUT_001",
					        "message": "해당 id의 PageLayout이 존재하지 않습니다."
					    },
					    "data": null
					}
					"""),
			}))
	})
	ResponseEntity<SduiResponse<PageLayoutResponse>> findById(Long id);

	@Operation(summary = "PageLayout 전체 조회")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "PageLayout 전체 조회 성공")
	})
	ResponseEntity<SduiResponse<List<PageLayoutResponse>>> findAll();

	@Operation(summary = "PageLayout 수정")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "PageLayout 수정 성공"),
		@ApiResponse(responseCode = "400", description = "입력 형식이 잘못됨", content = @Content(
			examples = {
				@ExampleObject(name = "해당 id의 PageLayout이 존재하지 않음", value = """
					{
					    "success": false,
					    "error": {
					        "code": "PAGE_LAYOUT_001",
					        "message": "해당 id의 PageLayout이 존재하지 않습니다."
					    },
					    "data": null
					}
					"""),
			}))
	})
	ResponseEntity<SduiResponse<PageLayoutResponse>> updateById(
		@PathVariable Long id,
		@RequestBody PageLayoutUpdatingRequest request
	);

	@Operation(summary = "PageLayout 삭제")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "204", description = "PageLayout 삭제 성공")
	})
	ResponseEntity<Void> deleteById(@PathVariable Long id);
}
