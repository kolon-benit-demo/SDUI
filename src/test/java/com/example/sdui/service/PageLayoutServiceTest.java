package com.example.sdui.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.sdui.domain.PageLayout;
import com.example.sdui.dto.request.PageLayoutRequest;
import com.example.sdui.dto.request.PageLayoutUpdatingRequest;
import com.example.sdui.dto.response.PageLayoutResponse;
import com.example.sdui.exception.SduiException;
import com.example.sdui.repository.PageLayoutRepository;
import com.example.sdui.support.DatabaseCleanUp;

@SpringBootTest
public class PageLayoutServiceTest {

	@Autowired
	private PageLayoutRepository pageLayoutRepository;

	@Autowired
	private PageLayoutService pageLayoutService;

	@Autowired
	private DatabaseCleanUp databaseCleanUp;

	private Long pageLayoutId;

	@BeforeEach
	void setUp() {
		databaseCleanUp.execute();
		PageLayout pageLayout = pageLayoutRepository.save(new PageLayout("sample", "{\"key\":\"value\"}"));
		pageLayoutId = pageLayout.getId();
	}

	@DisplayName("PageLayout을 저장한다.")
	@Test
	void page_layout_저장() {
		// given
		PageLayoutRequest request = new PageLayoutRequest("sample", "{\"key\":\"value\"}");

		// when
		Long saveId = pageLayoutService.save(request);

		// then
		assertThat(saveId).isNotNull();
	}

	@DisplayName("PageLayout 아이디로 조회한다.")
	@Test
	void page_layout_상세_조회_성공() {
		// given & when
		PageLayoutResponse pageLayoutResponse = pageLayoutService.findById(pageLayoutId);

		// then
		assertThat(pageLayoutResponse.getId()).isEqualTo(pageLayoutId);
	}

	@DisplayName("없는 PageLayout 아이디로 조회하면 예외가 발생한다.")
	@Test
	void page_layout_상세_조회_실패() {
		// given & when
		Long notExistPageLyaoutId = pageLayoutId + 1L;

		// then
		assertThatThrownBy(() -> pageLayoutService.findById(notExistPageLyaoutId))
			.isInstanceOf(SduiException.class);
	}

	@DisplayName("모든 PageLayout을 조회한다.")
	@Test
	void page_layout_전체_조회() {
		// given & when
		pageLayoutService.save(new PageLayoutRequest("sample2", "{\"key\":\"value\"}"));
		pageLayoutService.save(new PageLayoutRequest("sample3", "{\"key\":\"value\"}"));

		// then
		assertThat(pageLayoutService.findAll()).hasSize(3);
	}

	@DisplayName("PageLayout을 수정한다.")
	@Test
	void page_layout_수정() {
		// given
		PageLayoutUpdatingRequest pageLayoutUpdatingRequest = PageLayoutUpdatingRequest.builder()
			.name("sample2")
			.contents("{\"key2\":\"value2\"}")
			.build();

		// when
		PageLayoutResponse updatedPageLayout = pageLayoutService.updateById(pageLayoutId, pageLayoutUpdatingRequest);

		// then
		assertAll(
			() -> assertThat(updatedPageLayout.getName()).isEqualTo(pageLayoutUpdatingRequest.getName()),
			() -> assertThat(updatedPageLayout.getContents()).isEqualTo(pageLayoutUpdatingRequest.getContents())
		);
	}
}
