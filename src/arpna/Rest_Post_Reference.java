package arpna;
import static io.restassured.RestAssured.given;
import java.time.LocalDateTime;
import org.testng.Assert;
import io.restassured.path.json.JsonPath;
import io.restassured.RestAssured;
public class Rest_Post_Reference {

	public static void main(String[] args) {
		//Declear Base URL
     	RestAssured.baseURI = "https://reqres.in/";
		//Declear the RequestBody
		String requestBody = "{\r\n"
				+ "    \"name\": \"morpheus\",\r\n"
				+ "    \"job\": \"leader\"\r\n"
				+ "}";
		//Declear expected result
		JsonPath jsprequest = new JsonPath(requestBody);
		String req_name = jsprequest.getString("name");
		String req_job = jsprequest.getString("job");
		LocalDateTime currentdate = LocalDateTime.now();
		String expecteddate = currentdate.toString().substring(0, 11);
		//Declear given,when and then method
		String responseBody =given().header("Content-Type", "application/json").body(requestBody).when().post("api/users").then().extract().response().asString();
		//System.out.println(responseBody);
		// Create an object of JsonPath to parse the responseBody
		JsonPath jspresponse = new JsonPath(responseBody);
		String res_name = jspresponse.getString("name");
		String res_job = jspresponse.getString("job");
		String res_createdAt = jspresponse.getString("createdAt");
		res_createdAt = res_createdAt.substring(0, 11);
		//Validate the ResponsebodyŚ
		Assert.assertEquals(res_name, req_name);
		Assert.assertEquals(res_job, req_job);
		Assert.assertEquals(res_createdAt, expecteddate);
		//System.out.println(res_name);
		//System.out.println(res_job);
		//System.out.println(res_createdAt);
		System.out.println(responseBody);
		
	}

}
 