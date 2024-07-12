package com.example.sdui.divkit;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import divkit.dsl.Divan;

@RestController
@RequestMapping("/divkits")
public class DivkitController {

	@GetMapping("/sample")
	public ResponseEntity<Object> findSample() {
		Divan sample = Sample.getSample();
		return ResponseEntity.ok(sample);
	}
}

