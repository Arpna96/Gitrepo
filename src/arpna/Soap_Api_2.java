package arpna;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

import org.testng.Assert;

import io.restassured.path.xml.*;

public class Soap_Api_2 {

	public static void main(String[] args) {
		     RestAssured.baseURI = "https://www.dataaccess.com/";
		     String RequestBody = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n"
		     		+ "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n"
		     		+ "  <soap:Body>\r\n"
		     		+ "    <NumberToWords xmlns=\"http://www.dataaccess.com/webservicesserver/\">\r\n"
		     		+ "      <ubiNum>-50</ubiNum>\r\n"
		     		+ "    </NumberToWords>\r\n"
		     		+ "  </soap:Body>\r\n"
		     		+ "</soap:Envelope>";
		     String ResponseBody = given().header("Content-Type","text/xml; charset=utf-8").body(RequestBody).when().post("webservicesserver/NumberConversion.wso").then().extract().response().asPrettyString();
		     System.out.println(ResponseBody);
		     XmlPath XmlRes = new XmlPath(ResponseBody);
		     String ResponseParameter = XmlRes.getString("NumberToWordsResult");
		    System.out.println(ResponseParameter);
		     Assert.assertEquals(ResponseParameter,"number too large");
		    

	}

}
