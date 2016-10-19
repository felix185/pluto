package dhbw.pluto.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;

import dhbw.pluto.recipes.*;

@Path("/meta")
public class RecipeCollection {
	//create a recipe
	
	//show all recipes
	@GET
	@Path("/recipes")
	public Response show() {
		RecipeController rc = new RecipeController();
		rc.showRecipes();
		return Response.status(200).build(); 
	}
	//remove a recipe
	
	
}
