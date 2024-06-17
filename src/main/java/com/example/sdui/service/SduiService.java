package com.example.sdui.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.sdui.domain.Sdui;
import com.example.sdui.dto.request.SduiRequest;
import com.example.sdui.repository.SduiRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SduiService {

	private final SduiRepository sduiRepository;

	@Transactional
	public Long save(final SduiRequest request) {
		Sdui sdui = new Sdui(request.getJson());
		Sdui savedSdui = sduiRepository.save(sdui);
		return savedSdui.getId();
	}
}
