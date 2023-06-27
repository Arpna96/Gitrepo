package arpna;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

import org.testng.Assert;

import io.restassured.path.xml.*;

public class Soap_Api_1 {

	public static void main(String[] args) {
		// Declear the Base URL
		RestAssured.baseURI ="https://www.dataaccess.com/";
		//Declear the RequestBody
		String RequestBody = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n"
				+ "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n"
				+ "  <soap:Body>\r\n"
				+ "    <NumberToDollars xmlns=\"http://www.dataaccess.com/webservicesserver/\">\r\n"
				+ "      <dNum>399</dNum>\r\n"
				+ "    </NumberToDollars>\r\n"
				+ "  </soap:Body>\r\n"
				+ "</soap:Envelope>\r\n"
				+ "";
		//Extract ResponseBody
		String ResponseBody = given().header("Content-Type","text/xml; charset=utf-8").body(RequestBody).when().post("webservicesserver/NumberConversion.wso").then().extract().response().asString();
		System.out.println(ResponseBody);
		//parse the ResponseBody
		XmlPath XmlRes = new XmlPath(ResponseBody);
		String ResponseParameter = XmlRes.getString("NumberToDollarsResult");
		System.out.println(ResponseParameter);
		//Validate the Response Body
		Assert.assertEquals(ResponseParameter, "three hundred and ninety nine dollars");

	}

}
