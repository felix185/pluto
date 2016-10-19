package dhbw.pluto.api;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.json.JSONArray;

import dhbw.pluto.laundry.IconLoadingException;
import dhbw.pluto.laundry.LaundryAlert;
import dhbw.pluto.laundry.LaundryIcon;
import dhbw.pluto.laundry.LaundryIconLoader;


@Path("/laundry")
public class LaundryController {
	
	@GET
	@Path("/createAlert/{time}")
	public Response createAlert(@PathParam("time") String time) {
		LaundryAlert alert = new LaundryAlert("toksick89@gmail.com", time);
		System.out.println("Eingegebene Zeit: " + time);
		alert.initializeAlert();
		return Response.status(200).entity("Alarm erstellt").build();
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
