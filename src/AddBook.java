import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import File.ReusableMethod;
import File.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class AddBook {
	@Test (dataProvider = "BooksList")
	public void addBooks(String bookname, String bookId) {
		
		RestAssured.baseURI="http://216.10.245.166/";
		
		String resp= given()
                .log().all()
                .header("Content-Type", "application/json")
                .body(payload.AddBook(bookname,bookId))
                .when()
                .post("/Library/Addbook.php")
                .then().log().all().assertThat().statusCode(200).extract().response().asString();
		JsonPath js=ReusableMethod.rowToJson(resp);
		String ID=js.get("ID");

        System.out.println("ID: " + ID);
        
        //delete book entry
        
        String deleteResponse = given() .log().all()
                .header("Content-Type", "application/json")
                .body("{\"ID\" : \"" + ID + "\"}")
                .when()
                .post("/Library/DeleteBook.php")
                .then().log().all().assertThat().statusCode(200).extract().response().asString();

		        JsonPath jsDel = ReusableMethod.rowToJson(deleteResponse);
		        String msg = jsDel.getString("msg");
		        System.out.println(bookname+" Delete message" +ID+": "+ msg);
		        
    
		        
	}
	
	 // DataProvide Annotation: Add & Create multiple entries on one click
    
	 @DataProvider(name="BooksList")
	  public Object GetData()
	  {
		  return new Object[][]{{"Java1","1123"},{"Net1","5213"},{"Python1","4321"}};
		  
	  }
		

}
