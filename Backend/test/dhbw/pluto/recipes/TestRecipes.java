package dhbw.pluto.recipes;

import static org.junit.Assert.*;

import javax.ws.rs.core.Response;

import org.junit.Test;

import dhbw.pluto.api.LaundryController;
import dhbw.pluto.api.RecipeCollection;


public class TestRecipes {

	RecipeCollection recipeCollection = new RecipeCollection();
	LaundryController laundryController = new LaundryController();
	
	@Test
	public void testLaundryAlertValidParameters() {
		String email = "max@test.de";
		String time = "11:00";
		Response response = laundryController.createAlert(email, time);
		assertEquals(200, response.getStatus());
	}
	
	@Test
	public void testCreateRecipeValidParameters() {
		String validRecipe = "{\"text\": \"Wasser kochen, Brühwürfel darin auflösen, Nudeln dazu geben\",\"title\": \"Nudelsuppe\",\"eMail\": \"test@web.de\",\"ingredients\": [ {\"name\": \"Nudeln\", \"amount\": \"200\"}, {\"name\": \"Wasser\", \"amount\": \"3\"}, {\"name\": \"Brühwürfel\", \"amount\": \"1\"} ]}";
		Response response = recipeCollection.createRecipe(validRecipe);
		assertEquals(200, response.getStatus());
	}
	
	@Test
	public void testCreateRecipeInvalidParameters() {
		String invalidRecipe = "";
		Response response = recipeCollection.createRecipe(invalidRecipe);
		assertEquals(400, response.getStatus());
	}

	@Test
	public void testShowRecipes() {
		fail("Not yet implemented");
	}

	@Test
	public void testSearchRecipesValidParameters() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testSearchRecipesInvalidParameters() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteRecipeValidParameters() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testDeleteRecipeInvalidParameters() {
		fail("Not yet implemented");
	}

}
