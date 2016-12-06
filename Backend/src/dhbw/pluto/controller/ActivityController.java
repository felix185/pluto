package dhbw.pluto.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.json.JSONArray;

import dhbw.pluto.controller.exception.ActivityLoadingException;
import dhbw.pluto.database.ActivityDBHandler;
import dhbw.pluto.model.actvities.Activity;


@Path("/meta")
public class ActivityController {
		
	@GET
	@Path("/activities")
	public Response getActivities() {
		JSONArray result;
		try {
			result =  ActivityDBHandler.getActivities().toJSON();
		} catch(ActivityLoadingException e)	{
			return Response.status(500).build();			
		}
		return Response.status(200).entity(result.toString()).build();
	}
	
}
