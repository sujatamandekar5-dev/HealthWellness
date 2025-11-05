import static io.restassured.RestAssured.*;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import File.APIResource;
import io.restassured.path.json.JsonPath;
public  class GetResponceVerify extends BaseTest {
	@Test
	public void GetResponceAndVerify() throws IOException {
		PostCreate.createPost();
		String resp= given().queryParam("key", "qaclick123")
				.queryParam("place_id",PostCreate.placeId)
				.when().get(APIResource.getPlaceAPI())
				.then().assertThat().statusCode(200).extract().response().asString();
		 JsonPath js= new JsonPath(resp);
		 Assert.assertEquals(js.getString("name"), "Sujata Mandekar");
	        Assert.assertEquals(js.getString("address"), "401, Pan card club road, Rohan 09");
	        Assert.assertEquals(js.getString("language"), "French-IN");
	        Assert.assertEquals(js.getString("phone_number"),"(+91) 917 272 1679");
	        Assert.assertEquals(js.getString("types"), "Murkute park,Kohinoorshop");

	        System.out.println("✅ Verified record successfully with all fields matching!");
		
	}


}
