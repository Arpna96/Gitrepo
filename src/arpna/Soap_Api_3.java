package arpna;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

public class Soap_Api_3 {

	public static void main(String[] args) {
	RestAssured.basePath = "https://www.dataaccess.com/";
	String RequestBody = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n"
			+ "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n"
			+ "  <soap:Body>\r\n"
			+ "    <NumberToWords xmlns=\"http://www.dataaccess.com/webservicesserver/\">\r\n"
			+ "      <ubiNum>55.75</ubiNum>\r\n"
			+ "    </NumberToWords>\r\n"
			+ "  </soap:Body>\r\n"
			+ "</soap:Envelope>";
	String ResponseBody = given().header("Content-Type", "text/xml; charset=utf-8").body(RequestBody).when().post("webservicesserver/NumberConversion.wso").then().extract().response().asString();
	System.out.println(ResponseBody);
	}

}
