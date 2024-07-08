package com.example.sdui.acceptance;

import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.example.sdui.dto.request.PageLayoutRequest;
import com.example.sdui.support.DatabaseCleanUp;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

@SpringBootTest(properties = "spring.session.store-type=none", webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PageLayoutAcceptanceTest {

	@LocalServerPort
	int port;

	@Autowired
	private DatabaseCleanUp databaseCleanUp;

	@BeforeEach
	void setUp() {
		RestAssured.port = port;
		databaseCleanUp.execute();
	}

	@DisplayName("사용자가 PageLayout을 저장하고 200 OK를 반환한다.")
	@Test
	void create() {
		// given
		PageLayoutRequest request = new PageLayoutRequest("sample", "{\"key\":\"value\"}");

		// when
		ValidatableResponse response = RestAssured.given().log().all()
			.body(request)
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.accept(MediaType.APPLICATION_JSON_VALUE)
			.when().post("/page-layouts")
			.then().log().all();

		// then
		response.statusCode(HttpStatus.CREATED.value())
			.header("Location", notNullValue());
	}
}
