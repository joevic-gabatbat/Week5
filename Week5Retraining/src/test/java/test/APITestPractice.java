package test;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class APITestPractice {

	@Test(enabled = false)
	public void getResponseBody() {
		System.out.println("***** Get response body *****");
		RestAssured.given().when()
				.get("http://demo.guru99.com/V4/sinkministatement.php?CUSTOMER_ID=68195&PASSWORD=1234!&Account_No=1")
				.then().log().all();
	}

	@Test(enabled = false)
	public void getResponsStatus() {
		System.out.println("***** Get response status code *****");
		int statusCode = RestAssured.given().queryParam("CUSTOMER_ID", "68195").queryParam("PASSWORD", "1234!")
				.queryParam("Account_No", "1").when().get("http://demo.guru99.com/V4/sinkministatement.php")
				.getStatusCode();
		System.out.println("The response status is " + statusCode);
		String url = "http://demo.guru99.com/V4/sinkministatement.php";
		RestAssured.given().when().get(url).then().assertThat().statusCode(200);
	}

	@Test
	public void getMethod() {
		Response response = RestAssured.get("https://reqres.in/api/users?page=2");
		System.out.println(response.statusCode());
		System.out.println(response.getBody().asString());
		System.out.println(response.statusLine());
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
	}

	@Test
	public void postMethod() {
		JSONObject request = new JSONObject();
		request.put("name", "chaya");
		request.put("job", "BA");
		System.out.println(request.toString());
		RestAssured.given().body(request.toJSONString()).when().post("https://reqres.in/api/users").then()
				.statusCode(201);
	}

	@Test
	public void putMethod() {
		JSONObject request = new JSONObject();
		request.put("name", "chaya");
		request.put("job", "BAA");
		System.out.println(request.toString());
		RestAssured.given().body(request.toJSONString()).when().put("https://reqres.in/api/users/2").then()
				.statusCode(200);
	}

	@Test
	public void patchMethod() {

		JSONObject request = new JSONObject();
		request.put("name", "chaya");
		request.put("job", "BAA");
		System.out.println(request.toString());
		RestAssured.given().body(request.toJSONString()).when().patch("https://reqres.in/api/users/2").then()
				.statusCode(200);
	}

	@Test
	public void deleteMethod() {
		JSONObject request = new JSONObject();
		RestAssured.given().body(request.toJSONString()).when().delete("https://reqres.in/api/users/2").then()
				.statusCode(204).log().all();
	}
}
