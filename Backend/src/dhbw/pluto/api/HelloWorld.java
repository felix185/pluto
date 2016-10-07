package dhbw.pluto.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/hello-world")
public class HelloWorld {
	
	@GET
	@Path("/greetings/{name}")
	public Response greetPerson(@PathParam("name") String name) {
		String resultString = "Greetings from " + name + "!";
		return Response.status(200).entity(resultString).build();
	}

}
