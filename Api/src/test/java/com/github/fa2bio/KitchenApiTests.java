package com.github.fa2bio;

import static org.hamcrest.CoreMatchers.equalTo;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.fa2bio.domain.model.Kitchen;
import com.github.fa2bio.domain.repository.KitchenRepository;
import com.github.fa2bio.util.DatabaseCleaner;
import com.github.fa2bio.util.ResourceUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class KitchenApiTests {

	private static final int kitchenIdNonexistent = 100;
	private int qtyKitchensRegistered;
	private Kitchen americanKitchen;
	private String path;
	private String json;
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private DatabaseCleaner cleaner;
	
	@Autowired
	private KitchenRepository kitchenRepository;
	
	@Before
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/kitchens";
		
		path = "/json/status201.json";
		json = ResourceUtils.getContentFromResource(path);
		
		cleaner.clearTables();
		prepareData();
	}
	
	@Test
	public void shouldReturnStatus200_WhenConsultingKitchens() {
		RestAssured.given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void shouldConter2Kitchens_WhenConsultingKitchens() {
		RestAssured.given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.body("", Matchers.hasSize(this.qtyKitchensRegistered));
			//.body("name", Matchers.hasItems("Indiana", "Tailandesa"));
	}
	
	@Test
	public void shouldReturnStatus201_WhenCadastrarKitchen() {
		json = ResourceUtils.getContentFromResource(path);
		System.out.println(json);
		RestAssured.given()
			.body(json)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}
	
	@Test
	public void shouldReturnAnswerIsCorrect_WhenConsultingKitchenExisting() {
		RestAssured.given()
			.pathParam("kitchenId", this.americanKitchen.getId())
			.accept(ContentType.JSON)
		.when()
			.get("/{kitchenId}")
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("name", equalTo(this.americanKitchen.getName()));
	}
	
	@Test
	public void shouldReturnAnswerStatus404_WhenConsultExistingKitchen() {
		RestAssured.given()
			.pathParam("kitchenId", kitchenIdNonexistent)
			.accept(ContentType.JSON)
		.when()
			.get("/{kitchenId}")
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}
	private void prepareData() {
		Kitchen kitchen1 = new Kitchen();
		kitchen1.setName("Tailandesa");
		kitchenRepository.save(kitchen1);
	
		americanKitchen = new Kitchen();
		americanKitchen.setName("Americana");
		kitchenRepository.save(americanKitchen);
		
		qtyKitchensRegistered = (int) kitchenRepository.count();
	}
}