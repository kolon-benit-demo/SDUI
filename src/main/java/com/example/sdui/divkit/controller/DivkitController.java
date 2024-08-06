package com.example.sdui.divkit.controller;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sdui.common.dto.response.SduiResponse;
import com.example.sdui.common.exception.SduiException;
import com.example.sdui.divkit.exception.DivkitErrorCode;
import com.example.sdui.divkit.sample.Sample;

@RestController
@RequestMapping("/divkits")
public class DivkitController {

	private final Map<Integer, Sample> sampleMap;

	@Autowired
	public DivkitController(ApplicationContext applicationContext) {
		this.sampleMap = applicationContext.getBeansOfType(Sample.class)
			.values()
			.stream()
			.collect(Collectors.toMap(Sample::getId, sample -> sample));
	}

	@GetMapping("/sample/{id}")
	public ResponseEntity<SduiResponse<Object>> findSample(@PathVariable int id) {
		Sample sample = Optional.ofNullable(sampleMap.get(id))
			.orElseThrow(() -> new SduiException(DivkitErrorCode.SAMPLE_NOT_FOUND));
		return ResponseEntity.ok(SduiResponse.success(sample.getSample()));
	}
}

