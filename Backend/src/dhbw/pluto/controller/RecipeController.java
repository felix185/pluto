package dhbw.pluto.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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

import dhbw.pluto.controller.exception.ActivityCreationException;
import dhbw.pluto.controller.exception.RecipeCreationException;
import dhbw.pluto.controller.exception.RecipeDeletionException;
import dhbw.pluto.controller.exception.RecipeLoadingException;
import dhbw.pluto.database.ActivityDBHandler;
import dhbw.pluto.database.RecipeDBHandler;
import dhbw.pluto.model.Ingredient;
import dhbw.pluto.model.IngredientCollection;
import dhbw.pluto.model.actvities.RecipeCreationActivity;
import dhbw.pluto.model.actvities.RecipeDeletionActivity;
import dhbw.pluto.model.actvities.RecipeSearchActivity;

@Path("/meta")
public class RecipeController {
	//create a recipe
	@POST
	@Path("/recipe")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createRecipe(@FormParam("newRecipe") String newRecipe) {
		try {
			JSONObject newRcp = new JSONObject(newRecipe);
			JSONArray ingredients = newRcp.getJSONArray("ingredients");
			IngredientCollection ingre = new IngredientCollection();
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
				RecipeDBHandler.createRecipe(newRcp.getString("title"), newRcp.getString("text"), newRcp.getString("eMail"), ingre);
				try {
					ActivityDBHandler.writeActivity(new RecipeCreationActivity(newRcp.getString("eMail"), System.currentTimeMillis(), newRcp.getString("title")));
				} catch(ActivityCreationException e) {
					e.printStackTrace();
				}
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
	
	
	@POST
	@Path("/search")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchRecipe(@FormParam("ingredients") String ingredientsString) {
		JSONArray recipes = new JSONArray();
		try {
				
			JSONArray ingredients = new JSONArray(ingredientsString);			
			IngredientCollection ingre = new IngredientCollection();
			for(int i = 0; i < ingredients.length(); i++) {
			
				if (isIngredientValid(ingredients.getJSONObject(i))) {
					Ingredient ingredient = new Ingredient(ingredients.getJSONObject(i).getString("name"), ingredients.getJSONObject(i).getString("amount"));
					ingre.add(ingredient);
				} else {
					return Response.status(400).build();
				}
			}
			
			recipes = RecipeDBHandler.searchRecipes(ingre).toJSON();	
			try {
				ActivityDBHandler.writeActivity(new RecipeSearchActivity(System.currentTimeMillis(), ingre));
			} catch (ActivityCreationException e) {
				e.printStackTrace();
			}
			
		} catch (JSONException e) {
			System.out.println(e);
			return Response.status(400).build();
		} catch (RecipeLoadingException ex) {
			System.out.println(ex);
			return Response.status(400).build();
		}
		
		return Response.status(200).entity(recipes.toString()).build();
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
			result = RecipeDBHandler.showRecipes().toJSON();
		} catch(RecipeLoadingException e) {
			return Response.status(500).build();
		}
		
		return Response.status(200).entity(result.toString()).build(); 
	}
	
	
	//remove a recipe
	@DELETE
	@Path("/recipe/{id}")
	public Response removeRecipe(@PathParam("id") int id) {
		try {
			if (id > 0) {
				RecipeDBHandler.deleteRecipe(id);
			} else {
				return Response.status(400).build();
			}
		} catch(RecipeDeletionException e) {
			return Response.status(500).build();
		}
		try {
			ActivityDBHandler.writeActivity(new RecipeDeletionActivity(System.currentTimeMillis(), id));
		} catch(ActivityCreationException e) {
			e.printStackTrace();
		}
		return Response.status(200).build();
	}
	
}