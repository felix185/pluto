package dhbw.pluto.model;

import java.util.ArrayList;
import org.json.JSONArray;
import dhbw.pluto.controller.exception.ActivityLoadingException;
import dhbw.pluto.model.actvities.Activity;

public class ActivityCollection extends ArrayList<Activity> {
	
	public JSONArray toJSON() throws ActivityLoadingException {
		JSONArray result = new JSONArray();
		
		for(int i = 0; i < super.size(); i++) {
			result.put(super.get(i).toJSON());
		}		
		return result;
	}
}
