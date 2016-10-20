package dhbw.pluto.api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import dhbw.pluto.laundry.IconLoadingException;
import dhbw.pluto.laundry.LaundryAlert;
import dhbw.pluto.laundry.LaundryIcon;
import dhbw.pluto.laundry.LaundryIconLoader;


@Path("/laundry")
public class LaundryController {
	
	@POST
	@Path("/createAlert")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createAlert(@FormParam("newAlert") String newAlert) {
		
		LaundryAlert alert;
		try {
			JSONObject jsonAlert = new JSONObject(newAlert);
			alert = new LaundryAlert(jsonAlert.getString("emailAdress"),jsonAlert.getLong("time"));
			alert.initializeAlert();
			return Response.status(200).entity(alert.toString()).build();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(400).build();
		}
	}
	
	@GET
	@Path("/icons")
	public Response listIcons() {
		JSONArray result;
		try {
			result = convertIcons(LaundryIconLoader.loadLaundyIcons());
		} catch(IconLoadingException e) {
			return Response.status(501).build();
		}
		return Response.status(200).entity(result.toString()).build();
	}
	
	private JSONArray convertIcons(List<LaundryIcon> icons) {
		JSONArray result = new JSONArray();
		for(int i = 0; i < icons.size(); i++) {
			result.put(icons.get(i).toJSON());
		}
		return result;
	}
	

}
