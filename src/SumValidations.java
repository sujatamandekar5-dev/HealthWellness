import org.testng.Assert;
import org.testng.annotations.Test;

import File.ReusableMethod;
import File.payload;
import io.restassured.path.json.JsonPath;

public class SumValidations {
	
	@Test
	public void sumOfCources()
	{
		JsonPath js=ReusableMethod.rowToJson(payload.coursePrice());
	
		//JsonPath js=new JsonPath(payload.coursePrice());
		int purchaseAmt=js.getInt("dashboard.purchaseAmount");
		int count=	js.getInt("courses.size()");
		int Sum=0;
		for(int i=0;i<count;i++) 
		{
			int copies=js.getInt("courses["+i+"].copies");
			int Price=js.getInt("courses["+i+"].price");
			int cou=copies*Price;
			Sum=Sum+cou;
			System.out.println(" Cources price "+i+" : "+ cou);
			
		}
		System.out.println("Sum of prices: "+Sum);
		Assert.assertEquals(purchaseAmt, Sum);
		
	}

}
