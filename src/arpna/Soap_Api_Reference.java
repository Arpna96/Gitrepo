package arpna;
import static io.restassured.RestAssured.given;

import org.testng.Assert;

import io.restassured.path.xml.*;

import io.restassured.RestAssured;

public class Soap_Api_Reference {

	public static void main(String[] args) {
		//Declear the base URL
		RestAssured.baseURI = "https://www.dataaccess.com/";
		//Declear RequestBody
		String RequestBody = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n"
				+ "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n"
				+ "  <soap:Body>\r\n"
				+ "    <NumberToWords xmlns=\"http://www.dataaccess.com/webservicesserver/\">\r\n"
				+ "      <ubiNum>100</ubiNum>\r\n"
				+ "    </NumberToWords>\r\n"
				+ "  </soap:Body>\r\n"
				+ "</soap:Envelope>\r\n"
				+"";
		
				//Extract ResponseBody
		String ResponseBody = given().header("Content-Type", "text/xml; charset=utf-8").body(RequestBody).when().post("webservicesserver/NumberConversion.wso").then().extract().response().asString();
      System.out.println(ResponseBody);
      //parse responsebody
      XmlPath XmlResponse = new XmlPath(ResponseBody);
      String ResponseParameter = XmlResponse.getString("NumberToWordsResult");
      System.out.println(ResponseParameter);
      //validate the responsebody
      Assert.assertEquals(ResponseParameter,"one hundred ");
	}

}
