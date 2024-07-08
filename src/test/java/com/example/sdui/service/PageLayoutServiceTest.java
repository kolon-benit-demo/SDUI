package com.example.sdui.service;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.sdui.dto.request.PageLayoutRequest;
import com.example.sdui.support.DatabaseCleanUp;

@SpringBootTest
public class PageLayoutServiceTest {

	@Autowired
	private PageLayoutService pageLayoutService;

	@Autowired
	private DatabaseCleanUp databaseCleanUp;

	@BeforeEach
	void setUp() {
		databaseCleanUp.execute();
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
}
