package com.example.sdui.pagelayout.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.sdui.common.exception.SduiException;
import com.example.sdui.pagelayout.domain.PageLayout;
import com.example.sdui.pagelayout.domain.PageLayoutErrorCode;
import com.example.sdui.pagelayout.dto.request.PageLayoutRequest;
import com.example.sdui.pagelayout.dto.request.PageLayoutUpdatingRequest;
import com.example.sdui.pagelayout.dto.response.PageLayoutResponse;
import com.example.sdui.pagelayout.repository.PageLayoutRepository;

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

	@Transactional
	public PageLayoutResponse updateById(final Long id, final PageLayoutUpdatingRequest request) {
		PageLayout pageLayout = pageLayoutRepository.findById(id)
			.orElseThrow(() -> new SduiException(PageLayoutErrorCode.PAGE_LAYOUT_NOT_FOUND));

		PageLayout updatingPageLayoutInfo = request.toDomain();
		pageLayout.update(updatingPageLayoutInfo);

		return PageLayoutResponse.from(pageLayout);
	}

	@Transactional
	public void deleteById(final Long id) {
		pageLayoutRepository.deleteById(id);
	}
}
