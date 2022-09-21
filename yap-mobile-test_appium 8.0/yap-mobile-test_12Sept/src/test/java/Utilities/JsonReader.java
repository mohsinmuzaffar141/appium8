package Utilities;

import java.io.FileReader;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonReader {
	String jsonFilePath;
	
	public JsonReader(String filePath) {
		jsonFilePath = filePath;
	}
	
	public String getValue(String key) {
		JSONParser jsonParser = new JSONParser();
		String value = null;
	      try {
	    	  JSONObject jsonObject = (JSONObject)jsonParser.parse(new FileReader(jsonFilePath));
	         value = (String)jsonObject.get(key);
	      } catch(Exception e) {
	         e.printStackTrace();
	      }
		return value;
	}

	public String getValueFromJSONString(String jsonVal) throws ParseException {
		JsonParser parser = new JsonParser();
		JsonObject o = parser.parse(jsonVal).getAsJsonObject();
//		System.out.println("Array :  "+o.getAsJsonObject("body").getAsJsonObject("serviceData").getAsJsonObject("cardBalance").getAsJsonArray("accountDetails"));
		JsonArray ar =  o.getAsJsonObject("body").getAsJsonObject("serviceData").getAsJsonObject("cardBalance").getAsJsonArray("accountDetails");
//		System.out.println("first Index : "+ar.get(0));
		JsonObject ob = (JsonObject) ar.get(0);
		String value = null;
		value = ob.get("availableBalance").getAsString();
		System.out.println("Value : "+value);
		return value;
	}

}
