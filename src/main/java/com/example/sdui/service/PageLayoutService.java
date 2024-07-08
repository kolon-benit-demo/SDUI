package com.example.sdui.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.sdui.domain.PageLayout;
import com.example.sdui.dto.request.PageLayoutRequest;
import com.example.sdui.repository.PageLayoutRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PageLayoutService {

	private final PageLayoutRepository pageLayoutRepository;

	@Transactional
	public Long save(final PageLayoutRequest request) {
		PageLayout pageLayout = new PageLayout(request.getName(), request.getContents());
		PageLayout savedPageLayout = pageLayoutRepository.save(pageLayout);
		return savedPageLayout.getId();
	}
}
