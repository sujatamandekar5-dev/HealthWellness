package File;
// Common utilities
import io.restassured.path.json.JsonPath;

public class ReusableMethod {
	
	public static JsonPath rowToJson(String responce)
	{
		 JsonPath js1= new JsonPath(responce);
		 return js1;
	}

}
