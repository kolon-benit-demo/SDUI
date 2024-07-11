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

import com.example.sdui.domain.PageLayout;
import com.example.sdui.dto.request.PageLayoutRequest;
import com.example.sdui.dto.request.PageLayoutUpdatingRequest;
import com.example.sdui.repository.PageLayoutRepository;
import com.example.sdui.support.DatabaseCleanUp;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

@SpringBootTest(properties = "spring.session.store-type=none", webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PageLayoutAcceptanceTest {

	@LocalServerPort
	int port;

	@Autowired
	private DatabaseCleanUp databaseCleanUp;

	@Autowired
	private PageLayoutRepository pageLayoutRepository;

	private Long pageLayoutId;

	@BeforeEach
	void setUp() {
		RestAssured.port = port;
		databaseCleanUp.execute();
		PageLayout pageLayout = pageLayoutRepository.save(new PageLayout("sample", "{\"key\":\"value\"}"));
		pageLayoutId = pageLayout.getId();
	}

	@DisplayName("PageLayout을 저장하고 200 OK를 반환한다.")
	@Test
	void create() {
		// given
		PageLayoutRequest request = new PageLayoutRequest("sample", "{\"key\":\"value\"}");

		// when
		ValidatableResponse response = post("/page-layouts", request);

		// then
		response.statusCode(HttpStatus.CREATED.value())
			.header("Location", notNullValue());
	}

	@DisplayName("PageLayout 아이디로 조회하고 200 OK를 반환한다.")
	@Test
	void findById() {
		// given
		int id = pageLayoutId.intValue();

		// when
		ValidatableResponse response = get("/page-layouts/" + id);

		// then
		response.statusCode(HttpStatus.OK.value())
			.body("id", equalTo(id));
	}

	@DisplayName("모든 PageLayout을 조회하고 200 OK를 반환한다.")
	@Test
	void findAll() {
		// given
		pageLayoutRepository.save(new PageLayout("sample2", "{\"key\":\"value\"}"));
		pageLayoutRepository.save(new PageLayout("sample3", "{\"key\":\"value\"}"));

		// when
		ValidatableResponse response = get("/page-layouts");

		// then
		response.statusCode(HttpStatus.OK.value())
			.body("size()", equalTo(3));
	}

	@DisplayName("PageLayout을 수정하고 200 OK를 반환한다.")
	@Test
	void update() {
		// given
		PageLayoutUpdatingRequest pageLayoutUpdatingRequest = PageLayoutUpdatingRequest.builder()
			.name("sample2")
			.contents("{\"key2\":\"value2\"}")
			.build();

		// when
		ValidatableResponse response = patch("/page-layouts/" + pageLayoutId, pageLayoutUpdatingRequest);

		// then
		response.statusCode(HttpStatus.OK.value())
			.body("name", equalTo(pageLayoutUpdatingRequest.getName()))
			.body("contents", equalTo(pageLayoutUpdatingRequest.getContents()));
	}

	private ValidatableResponse get(final String uri) {
		return RestAssured.given().log().all()
			.accept(MediaType.APPLICATION_JSON_VALUE)
			.when().get(uri)
			.then().log().all();
	}

	private ValidatableResponse post(final String uri, final Object requestBody) {
		return RestAssured.given().log().all()
			.body(requestBody)
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.accept(MediaType.APPLICATION_JSON_VALUE)
			.when().post(uri)
			.then().log().all();
	}

	private ValidatableResponse patch(final String uri, final Object requestBody) {
		return RestAssured.given().log().all()
			.body(requestBody)
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.accept(MediaType.APPLICATION_JSON_VALUE)
			.when().patch(uri)
			.then().log().all();
	}
}
