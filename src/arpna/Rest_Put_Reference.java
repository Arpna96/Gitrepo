package arpna;
import static io.restassured.RestAssured.given;

import java.time.LocalDateTime;

import org.testng.Assert;

import io.restassured.path.json.JsonPath;
import io.restassured.RestAssured;
public class Rest_Put_Reference {

	public static void main(String[] args) {
		// Declear Base URL
		RestAssured.baseURI = "https://reqres.in/";
		//Declear Request body String variable
		String requestBody = "{\r\n"
				+ "    \"name\": \"morpheus\",\r\n"
				+ "    \"job\": \"zion resident\"\r\n"
				+ "}";
		//Declear the expected Result
		JsonPath jspRequest = new JsonPath(requestBody);
		String req_name = jspRequest.getString("name");
		String req_job = jspRequest.getString("job");
		LocalDateTime currentdate = LocalDateTime.now();
		String expecteddate = currentdate.toString().substring(0,11);
		//Declear given,when,then method
		String responseBody = given().header("Content-Type","application/json").body(requestBody).when().put("api/users/2").then().extract().response().asString();
		//create the an object of jasonpath to parse the responsebody
		JsonPath jspResponse = new JsonPath(responseBody);
		String res_name = jspResponse.getString("name");
		String res_job = jspResponse.getString("job");
		String res_updatedAt = jspResponse.getString("updatedAt");
		res_updatedAt = res_updatedAt.substring(0,11);
		//validate the responseBody parameters
		Assert.assertEquals(res_name, req_name);
		Assert.assertEquals(res_job, req_job);
		Assert.assertEquals(res_updatedAt, expecteddate);
		
		//System.out.println(res_name);
		//System.out.println(res_job);
		//System.out.println(res_updatedAt);
        System.out.println(responseBody);
	}

}
