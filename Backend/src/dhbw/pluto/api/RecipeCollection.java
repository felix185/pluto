package dhbw.pluto.api;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import dhbw.pluto.recipes.*;

@Path("/meta")
public class RecipeCollection {
	//create a recipe
	@POST
	@Path("/recipe")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createRecipe(@FormParam("newRecipe") String newRecipe) {
		try {
			JSONObject newRcp = new JSONObject(newRecipe);
			JSONArray ingredients = newRcp.getJSONArray("ingredients");
			List<Ingredient> ingre = new ArrayList<Ingredient>();
			for(int i = 0; i < ingredients.length(); i++) {
				//check if not null
				if (isIngredientValid(ingredients.getJSONObject(i))) {
					Ingredient ingredient = new Ingredient(ingredients.getJSONObject(i).getString("name"), ingredients.getJSONObject(i).getString("amount"));
					ingre.add(ingredient);
				} else {
					return Response.status(400).build();
				}
				
			}
			if (!newRcp.getString("title").equals("") && !newRcp.getString("text").equals("") && !newRcp.getString("eMail").equals("")) {
				RecipeController.createRecipe(newRcp.getString("title"), newRcp.getString("text"), newRcp.getString("eMail"), ingre);
			} else {
				return Response.status(400).build();
			}
			
		} catch (JSONException e) {
			return Response.status(400).build();
		} catch (RecipeCreationException ex) {
			return Response.status(400).build();
		}
		
		return Response.status(200).build();
	}
	
	private boolean isIngredientValid(JSONObject ingredient) throws JSONException{
		return !ingredient.getString("name").equals("") && !ingredient.getString("amount").equals("");
	}
	
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

	private JSONArray convertRecipes(List<Recipe> recipes) {
		JSONArray result = new JSONArray();
		for (int i = 0; i < recipes.size(); i++) {
			result.put(recipes.get(i).toJSON());
		}
		return result;
	}
	
	
	//remove a recipe
	@DELETE
	@Path("/recipe/{id}")
	public Response removeRecipe(@PathParam("id") int id) {
		try {
			if (id > 0) {
				RecipeController.deleteRecipe(id);
			} else {
				return Response.status(400).build();
			}
		} catch(RecipeDeletionException e) {
			return Response.status(500).build();
		}
		
		return Response.status(200).build();
	}
	
}