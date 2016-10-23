package dhbw.pluto.api;

import java.util.List;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;

import dhbw.pluto.activities.ActivityController;
import dhbw.pluto.activities.ActivityCreationException;
import dhbw.pluto.activities.LaundryReminderCreationActivity;
import dhbw.pluto.activities.RecipeCreationActivity;
import dhbw.pluto.laundry.IconLoadingException;
import dhbw.pluto.laundry.LaundryAlert;
import dhbw.pluto.laundry.LaundryIcon;
import dhbw.pluto.laundry.LaundryIconLoader;


@Path("/laundry")
public class LaundryController {
	
	@POST
	@Path("/createAlert")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response createAlert(@FormParam("email") String email, @FormParam("time") String time) {
		
		try {
			InternetAddress adr = new InternetAddress(email);
			adr.validate();
		} catch (AddressException e) {
			return Response.status(400).entity("Das ist keine E-Mail-Adresse!").build();
		}
		
		LaundryAlert alert = new LaundryAlert(email, time);
		alert.initializeAlert();
		try {
			ActivityController.writeActivity(new LaundryReminderCreationActivity(System.currentTimeMillis(), email, time));
		} catch (ActivityCreationException e) {
			e.printStackTrace();
		}
		return Response.status(200).entity(alert.toString()).build();

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
