package dhbw.pluto.model;

import java.util.ArrayList;

import org.json.JSONArray;

public class LaundryIconCollection extends ArrayList<LaundryIcon>{
	
	public JSONArray toJSON() {
		JSONArray result = new JSONArray();
		for(int i = 0; i < super.size(); i++) {
			result.put(super.get(i).toJSON());
		}
		return result;
	}
}
