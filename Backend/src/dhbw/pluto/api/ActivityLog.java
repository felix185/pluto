package dhbw.pluto.api;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.json.JSONArray;

import dhbw.pluto.activities.Activity;
import dhbw.pluto.activities.ActivityController;
import dhbw.pluto.activities.ActivityLoadingException;


@Path("/meta")
public class ActivityLog {
		
	@GET
	@Path("/activities")
	public Response getActivities() {
		JSONArray result;
		try {
			result = convertActivities(ActivityController.getActivities());
		} catch(ActivityLoadingException e)
		{
			return Response.status(501).build();			
		}
		return Response.status(200).entity(result.toString()).build();
	}
	
	private JSONArray convertActivities(List<Activity> activities) throws ActivityLoadingException {
		JSONArray result = new JSONArray();
		
		for(int i = 0; i < activities.size(); i++) {
			result.put(activities.get(i).toJSON());
		}		
		return result;
	}
	
}
