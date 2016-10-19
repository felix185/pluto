package dhbw.pluto.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import dhbw.pluto.laundry.LaundryAlert;


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
	

}
