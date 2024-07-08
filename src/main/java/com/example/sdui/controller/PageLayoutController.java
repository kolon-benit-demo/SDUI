package com.example.sdui.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sdui.dto.request.PageLayoutRequest;
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
}
