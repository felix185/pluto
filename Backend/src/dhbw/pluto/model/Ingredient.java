package dhbw.pluto.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Ingredient {
	
	private String name;
	private String amount;
	
	public Ingredient (String name, String amount) {
		this.name = name;
		this.amount = amount;
		
		if (name == null) {
			System.out.println("Name ist null");
		}
		
	}
	
	//generate valid JSON
		public JSONObject toJSON() {
		    JSONObject result = new JSONObject();

		    try {
		        result.put("name", name);
		        result.put("amount", amount);
		    } catch (JSONException e) {
		        // put() throws e, if key==null. Since key=<STRING>, this exception should never be thrown.
		        e.printStackTrace();
		    }

		    return result;
		}
		
		public String getName() {
			return name;
		}
		
		public String getAmount() {
			return amount;
		}

}
