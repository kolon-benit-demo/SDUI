package com.example.sdui.service;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.sdui.dto.request.SduiRequest;
import com.example.sdui.support.DatabaseCleanUp;

@SpringBootTest
public class SduiServiceTest {

	@Autowired
	private SduiService sduiService;

	@Autowired
	private DatabaseCleanUp databaseCleanUp;

	@BeforeEach
	void setUp() {
		databaseCleanUp.execute();
	}

	@DisplayName("json를 저장한다.")
	@Test
	void json_저장() {
		// given
		SduiRequest request = new SduiRequest("{\"key\":\"value\"}");

		// when
		Long saveId = sduiService.save(request);

		// then
		assertThat(saveId).isNotNull();
	}
}
