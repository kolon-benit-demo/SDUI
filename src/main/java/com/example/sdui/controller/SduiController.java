package com.example.sdui.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sdui.dto.request.SduiRequest;
import com.example.sdui.service.SduiService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/sdui")
@RequiredArgsConstructor
public class SduiController implements SduiControllerDocs {

	private final SduiService sduiService;

	@PostMapping
	public ResponseEntity<Void> create(@RequestBody @Valid final SduiRequest request) {
		Long id = sduiService.save(request);
		return ResponseEntity.created(URI.create("/sdui/" + id)).build();
	}
}
