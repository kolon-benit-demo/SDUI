package com.example.sdui.controller;

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

import com.example.sdui.dto.request.PageLayoutRequest;
import com.example.sdui.dto.request.PageLayoutUpdatingRequest;
import com.example.sdui.dto.response.PageLayoutResponse;
import com.example.sdui.service.PageLayoutService;

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
	public ResponseEntity<PageLayoutResponse> findById(@PathVariable Long id) {
		PageLayoutResponse response = pageLayoutService.findById(id);
		return ResponseEntity.ok(response);
	}

	@GetMapping
	public ResponseEntity<List<PageLayoutResponse>> findAll() {
		List<PageLayoutResponse> responses = pageLayoutService.findAll();
		return ResponseEntity.ok(responses);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<PageLayoutResponse> updateById(
		@PathVariable Long id,
		@RequestBody PageLayoutUpdatingRequest request
	) {
		PageLayoutResponse response = pageLayoutService.updateById(id, request);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		pageLayoutService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
