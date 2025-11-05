package File;
// All end points in one place 
public class APIResource {
	public static String addPlaceAPI() {
		  return "/maps/api/place/add/json";
	}

	 public static String getPlaceAPI() {
	        return "/maps/api/place/get/json";
	    }
	 
	 public static String updatePlaceAPI() {
	        return "/maps/api/place/update/json";
	    }
}
