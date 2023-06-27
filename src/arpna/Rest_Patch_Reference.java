package arpna;
import static io.restassured.RestAssured.given;

import java.time.LocalDateTime;

import org.testng.Assert;

import io.restassured.path.json.JsonPath;
import io.restassured.RestAssured;

public class Rest_Patch_Reference {

	public static void main(String[] args) {
		RestAssured.baseURI = "https://reqres.in/";
		String requestBody = "{\r\n"
				+ "    \"name\": \"morpheus\",\r\n"
				+ "    \"job\": \"zion resident\"\r\n"
				+ "}";
		JsonPath JspRequest = new JsonPath(requestBody);
		String req_name = JspRequest.getString("name");
		String req_job = JspRequest.getString("job");
		LocalDateTime currenttime = LocalDateTime.now();
		String expecteddate = currenttime.toString().substring(0, 11);
       String ResponseBody = given().header("Content-Type", "application/json").body(requestBody).when().patch("api/users/2").then().extract().response().asString();
       //String ResponseBody = given().header("Content-Type", "appliction/json").body(requestBody).log().all().when().patch("api/users/2").then().log().all().extract().response().asString();
       System.out.println(ResponseBody);
       JsonPath JspResponse = new JsonPath(ResponseBody);
       String res_name = JspResponse.getString("name");
       String res_job = JspResponse.getString("job");
       String res_updatedAt = JspResponse.getString("updatedAt");
       res_updatedAt = res_updatedAt.substring(0, 11);
       Assert.assertEquals(res_name, req_name);
       Assert.assertEquals(res_job, req_job);
       Assert.assertEquals(res_updatedAt, expecteddate);
       //System.out.println(res_name);
       //System.out.println(res_job);
      // System.out.println(res_createdAt);
       
       
	}

}
