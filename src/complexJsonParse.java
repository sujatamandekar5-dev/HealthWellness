import org.testng.Assert;

import File.payload;
import io.restassured.path.json.JsonPath;

public class complexJsonParse {
	public static void main(String[] args) {
		
		JsonPath js=new JsonPath(payload.coursePrice());
		// 1.Print No of courses returned by API
		// here is the array of cources so we are able to find the size of it  
	int count=	js.getInt("courses.size()");
	System.out.println("cource count="+ count);
	
	//  2.Print Purchase Amount
	int purchaseAmt=js.getInt("dashboard.purchaseAmount");
	System.out.println("purchase count="+ purchaseAmt);
	
	
	// 3. Print Title of the first course
	// get method pull of string default 
	String titleOfFirstCource= js.get("courses[0].title");
	System.out.println("First Cource Title: "+titleOfFirstCource);
	
	//4.Print All course titles and their respective Prices
	for(int i=0;i<count;i++) 
	{
		String Title=js.get("courses["+i+"].title");
		int Price=js.getInt("courses["+i+"].price");
		System.out.println(" Cources Title "+i+" : "+ Title);
		System.out.println(" Cources price "+i+" : "+ Price);
		
	}
	
	
	//5. Print no of copies sold by RPA Course
	for(int i=0;i<count;i++)
	{
		String Title=js.get("courses["+i+"].title");
		if(Title.equalsIgnoreCase("RPA")) {
			int copies=js.getInt("courses["+i+"].copies");
			System.out.println("RPA Copies: "+copies);
			break; // stop for loop once find data 
		}
	
	}
	
	//6. Verify if Sum of all Course prices matches with Purchase Amount
	int Sum=0;
	for(int i=0;i<count;i++) 
	{
		int copies=js.getInt("courses["+i+"].copies");
		int Price=js.getInt("courses["+i+"].price");
		int cou=copies*Price;
		Sum=Sum+cou;
		System.out.println(" Cources price "+i+" : "+ Price);
		
	}
	
	Assert.assertEquals(purchaseAmt, Sum);
	System.out.println("Sum of prices: "+Sum);
	
	
	}
}