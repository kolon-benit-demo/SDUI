package com.example.sdui.divkit.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sdui.divkit.Sample;
import com.example.sdui.dto.response.SduiResponse;

import divkit.dsl.Divan;

@RestController
@RequestMapping("/divkits")
public class DivkitController {

	@GetMapping("/sample")
	public ResponseEntity<SduiResponse<Object>> findSample() {
		Divan sample = Sample.getSample();
		return ResponseEntity.ok(SduiResponse.success(sample));
	}
}

