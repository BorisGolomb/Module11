package com.epam.webservices.training.tests;

import static io.restassured.RestAssured.given;

import com.epam.webservices.training.model.user.User;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.epam.webservices.training.model.post.Post;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RestAssuredTest {

	@BeforeTest
	public void initTest() {
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";
	}
	
	@Test
	public void checkStatusCode1() {
		Response rp = given().get("/users").andReturn();
		int actualStatusCode = rp.getStatusCode();
		System.out.println(actualStatusCode);
		Assert.assertEquals(actualStatusCode, 200);
	}
	
	@Test
	public void checkResponseHeader() {
		Response rp = given().get("/users").andReturn();
		String valueOfContentTypeHeader = rp.getHeader("content-type");
		Assert.assertTrue(valueOfContentTypeHeader.contains("application/json"));
	}
	@Test
	public void checkResponseBody() {
		Response rp = given().get("/users").andReturn();
		User[] users = rp.as(User[].class);
		Assert.assertEquals(users.length, 10);
	}
	
}
