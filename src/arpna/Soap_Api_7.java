package arpna;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.path.xml.*;

public class Soap_Api_7 {

	public static void main(String[] args) {
		RestAssured.baseURI = "https://www.dataaccess.com/";
		String ResquestBody = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n"
				+ "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n"
				+ "  <soap:Body>\r\n"
				+ "    <NumberToDollars xmlns=\"http://www.dataaccess.com/webservicesserver/\">\r\n"
				+ "      <dNum>7/5</dNum>\r\n"
				+ "    </NumberToDollars>\r\n"
				+ "  </soap:Body>\r\n"
				+ "</soap:Envelope>";
		String ResponseBody = given().header("Content-Type","text/xml; charset=utf-8").body(ResquestBody).when().post("webservicesserver/NumberConversion.wso").then().extract().response().asString();
		System.out.println(ResponseBody);
		XmlPath XmlRes = new XmlPath(ResponseBody);
		String ResParameter = XmlRes.getString("NumberToDollarsResult");
		System.out.println(ResParameter);
		

	}

}
