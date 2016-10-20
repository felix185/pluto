package dhbw.pluto.api;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.json.JSONArray;

import java.util.List;

import dhbw.pluto.recipes.*;

@Path("/meta")
public class RecipeCollection {
	//create a recipe
	
	//show all recipes
	@GET
	@Path("/recipes")
	public Response listRecipes() {
		JSONArray result;
		try {
			result = convertRecipes(RecipeController.showRecipes());
		} catch(RecipeLoadingException e) {
			return Response.status(500).build();
		}
		
		return Response.status(200).entity(result.toString()).build(); 
	}

	//remove a recipe
	
	
	private JSONArray convertRecipes(List<Recipe> recipes) {
		JSONArray result = new JSONArray();
		for (int i = 0; i < recipes.size(); i++) {
			result.put(recipes.get(i).toJSON());
		}
		return result;
	}
	
	
}
