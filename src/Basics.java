
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.testng.Assert;

import File.ReusableMethod;
import File.payload;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
public class Basics {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//given- all input details 
		//When= Submit the API - rESource & http method
		//Then- Validate the responce 
		
		  // Given - all input details
        // When - Submit the API
        // Then - Validate the response

		// Post()- Create / Add place 
        RestAssured.baseURI = "https://rahulshettyacademy.com";
       String responce= given().log().all().queryParam("key","qaclick123")
        .header("Content-Type","application/json")
        .body(payload.AddPlace())
        .when().post("/maps/api/place/add/json")
        .then().assertThat().statusCode(200).body("scope", equalTo("APP"))
        .extract().response().asString(); // get responce in staring 
        
        System.out.println("Responce Status = "+responce);
        
        JsonPath js = new JsonPath(responce); // helps to take string as input and convrt to json
       String placeID= js.getString("place_id");
        
       System.out.println(" PlaceID = "+placeID);
        
       
       //Add place using external file
       RestAssured.baseURI = "https://rahulshettyacademy.com";
       String responce1= given().log().all().queryParam("key","qaclick123")
        .header("Content-Type","application/json")
        .body(new String(Files.readAllBytes(Paths.get("C:\\Users\\mahes\\eclipse-workspace\\API Automation\\src\\Tests\\Resorces\\AddPlace.json"))))
        .when().post("/maps/api/place/add/json")
        .then().assertThat().statusCode(200).body("scope", equalTo("APP"))
        .extract().response().asString(); // get responce in staring 
        
        System.out.println("Responce Status = "+responce1);
        
        JsonPath js2 = new JsonPath(responce1); // helps to take string as input and convrt to json
       String placeID2= js2.getString("place_id");
        
       System.out.println(" PlaceID = "+placeID2);
       
       
       //Update address
       String newAddress="601, Pan card club road, Rohan 09";
       given().log().all().queryParam("key","qaclick123")
       .header("Content-Type","application/json")
       .body("{ \"place_id\": \""+placeID+"\",\r\n"
       		+ "  \"address\": \""+newAddress+"\",\r\n"
       		+ "  \"key\":\"qaclick123\"\r\n"
       		+ "  }")
       .when().put("maps/api/place/update/json")
       .then().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
      

       
       // Get place - should not add header & body... but need to send place id in param
      String getPlaceResponce= given().log().all().queryParam("key","qaclick123")
       .queryParam("place_id", placeID)
       .when().get("maps/api/place/get/json")
       .then().log().all().statusCode(200).extract().response().asString();
       
      JsonPath js1= ReusableMethod.rowToJson(getPlaceResponce);
     String actualdAddress= js1.getString("address");
      Assert.assertEquals(actualdAddress, newAddress);
      System.out.println(actualdAddress);
     
//        // Example request (you can modify as needed)
//        RequestSpecification request = RestAssured.given()
//                .queryParam("key", "qaclick123")
//                .header("Content-Type", "application/json");
//
//        Response response = request.get("/maps/api/place/get/json");
//
//        System.out.println("Response Code: " + response.getStatusCode());
//        System.out.println("Response Body: " + response.asString());

	}

}
