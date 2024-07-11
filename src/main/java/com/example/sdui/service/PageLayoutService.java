package com.example.sdui.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.sdui.domain.PageLayout;
import com.example.sdui.domain.PageLayoutErrorCode;
import com.example.sdui.dto.request.PageLayoutRequest;
import com.example.sdui.dto.response.PageLayoutResponse;
import com.example.sdui.exception.SduiException;
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

	public PageLayoutResponse findById(final Long id) {
		PageLayout pageLayout = pageLayoutRepository.findById(id)
			.orElseThrow(() -> new SduiException(PageLayoutErrorCode.PAGE_LAYOUT_NOT_FOUND));
		return PageLayoutResponse.from(pageLayout);
	}

	public List<PageLayoutResponse> findAll() {
		List<PageLayout> pageLayouts = pageLayoutRepository.findAll();
		return pageLayouts.stream()
			.map(PageLayoutResponse::from)
			.collect(Collectors.toList());
	}
}
