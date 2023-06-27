package arpna;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;

public class Rest_Delete_Reference {

	public static void main(String[] args) {
		RestAssured.baseURI = "https://reqres.in/";
		String ResponseBody  = given().header("Content-Type", "application/json") .when().delete("api/users/2").then().extract().response().asString();
		System.out.println(ResponseBody);
		

	}

}
