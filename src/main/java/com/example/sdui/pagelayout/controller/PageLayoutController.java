package com.example.sdui.pagelayout.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sdui.common.dto.response.SduiResponse;
import com.example.sdui.pagelayout.dto.request.PageLayoutRequest;
import com.example.sdui.pagelayout.dto.request.PageLayoutUpdatingRequest;
import com.example.sdui.pagelayout.dto.response.PageLayoutResponse;
import com.example.sdui.pagelayout.service.PageLayoutService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/page-layouts")
@RequiredArgsConstructor
public class PageLayoutController implements PageLayoutControllerDocs {

	private final PageLayoutService pageLayoutService;

	@PostMapping
	public ResponseEntity<Void> create(@RequestBody @Valid final PageLayoutRequest request) {
		Long id = pageLayoutService.save(request);
		return ResponseEntity.created(URI.create("/page-layouts/" + id)).build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<SduiResponse<PageLayoutResponse>> findById(@PathVariable Long id) {
		PageLayoutResponse response = pageLayoutService.findById(id);
		return ResponseEntity.ok(SduiResponse.success(response));
	}

	@GetMapping
	public ResponseEntity<SduiResponse<List<PageLayoutResponse>>> findAll() {
		List<PageLayoutResponse> responses = pageLayoutService.findAll();
		return ResponseEntity.ok(SduiResponse.success(responses));
	}

	@PatchMapping("/{id}")
	public ResponseEntity<SduiResponse<PageLayoutResponse>> updateById(
		@PathVariable Long id,
		@RequestBody PageLayoutUpdatingRequest request
	) {
		PageLayoutResponse response = pageLayoutService.updateById(id, request);
		return ResponseEntity.ok(SduiResponse.success(response));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		pageLayoutService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
