package org.Billie.Core;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonReader;

import org.Billie.resources.TestData;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class MainClass implements TestData {

	String responseBody = "";;
	JsonArray array;
	int value = 0;

	public void setResponseBody(String responseBody) {
		this.responseBody = responseBody;
	}

	public void setArray(JsonArray array) {
		this.array = array;
	}

	public String getResponseBody() {
		return responseBody;
	}

	public JsonArray getArray() {
		return array;
	}
	
	/**
	 * @Desc : TO fetch data from URL provided
	 */

	@BeforeClass
	public void getDataFromURL() {
		try {
			RequestSpecification httpRequest = RestAssured.given();
			System.out.println("=> Obtaininig response from URL : " + urlKey + "\n");
			Response response = httpRequest.request(Method.GET, urlKey);
			int responseCode = response.getStatusCode();

			if(responseCode ==200) {
				setResponseBody(response.getBody().asString());
				JsonReader reader = Json.createReader(new StringReader(responseBody));
				setArray(reader.readArray());

			}else {
				System.out.print("--> Response Code Invalid\n");
				Assert.fail("--> Response Not proper / Response code invalid");
			}
		} catch (Exception e) {
			System.out.println("--> Exception found in Method : getData " + e.toString());
		}

	}
}