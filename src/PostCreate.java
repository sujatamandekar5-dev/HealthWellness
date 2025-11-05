import java.io.IOException;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;
//import File.APIRsource;
import File.ReusableMethod;
import File.payload;
import io.restassured.path.json.JsonPath;

import static org.hamcrest.Matchers.equalTo;


public class PostCreate extends BaseTest {
	  public static String placeId;
	@Test
	public static void createPost() throws IOException
	{
		// Add place 
		String responce = given().log().all().queryParam("key", "qaclick123")
				.header("Content-Type", "application/json")
				.body(payload.AddPlace())
				.when().post("maps/api/place/add/json")
				.then().assertThat().statusCode(200).body("scope",equalTo("APP"))
				.extract().response().asString();
		JsonPath js = new JsonPath(responce);  // Converts String to JSON
         placeId = js.getString("place_id");  // ✅ Correct key name
        System.out.println("Place ID: " + placeId);
                

	}

}
