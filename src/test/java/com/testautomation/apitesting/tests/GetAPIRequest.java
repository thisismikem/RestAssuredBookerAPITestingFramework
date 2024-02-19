package com.testautomation.apitesting.tests;

import io.restassured.path.json.JsonPath;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GetAPIRequest {
	
	@Test
	public void getAllBookings() {
		
		Response response =
				given()
						.contentType(ContentType.JSON)
						.baseUri("https://restful-booker.herokuapp.com/booking")
				.when()
						.get()
				.then()
						.assertThat()
						.statusCode(200)
						.statusLine("HTTP/1.1 200 OK")
						.header("Content-Type", "application/json; charset=utf-8")
						.body("$.size()", Matchers.greaterThan(0))
				.extract()
						.response();

		Assert.assertTrue(response.getBody().asString().contains("bookingid"));

		JsonPath jsonPath = response.jsonPath();
		Assert.assertTrue(jsonPath.getList("$").size() > 0);

	}

}
