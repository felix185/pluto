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
	public void testLaundryAlertInvalidParameters() {
		String email = "amx";
		String time = "11:00";
		Response response = laundryController.createAlert(email, time);
		assertEquals(400, response.getStatus());
	}
	
	@Test
	public void testCreateRecipeValidParameters() {
		String validRecipe = "{\"text\": \"Wasser kochen, Brühwürfel darin auflösen, Nudeln dazu geben\",\"title\": \"Nudelsuppe\",\"eMail\": \"test@web.de\",\"ingredients\": [ {\"name\": \"Nudeln\", \"amount\": \"200\"}, {\"name\": \"Wasser\", \"amount\": \"3\"}, {\"name\": \"Brühwürfel\", \"amount\": \"1\"} ]}";
		Response response = recipeCollection.createRecipe(validRecipe);
		assertEquals(200, response.getStatus());
	}
	
	@Test
	public void testCreateRecipeInvalidParameters() {
		String invalidRecipe = "{\"text\": \"Wasser kochen, Brühwürfel darin auflösen, Nudeln dazu geben\",\"eMail\": \"test@web.de\",\"ingredients\": [ {\"name\": \"Nudeln\", \"amount\": \"200\"}, {\"name\": \"Wasser\", \"amount\": \"3\"}, {\"name\": \"Brühwürfel\", \"amount\": \"1\"} ]}";
		Response response = recipeCollection.createRecipe(invalidRecipe);
		assertEquals(400, response.getStatus());
	}

	@Test
	public void testSearchRecipesValidParameters() {
		String validSearchParameter = "[{\"name\":\"Wasser\", \"amount\":\"3\"}, {\"name\":\"Brühwürfel\", \"amount\":\"1\"}, {\"name\":\"Nudeln\", \"amount\":\"200\"}]";
		Response response = recipeCollection.searchRecipe(validSearchParameter);
		assertEquals(200, response.getStatus());
	}
	
	@Test
	public void testSearchRecipesInvalidParameters() {
		String invalidSearchParameter = "[{\"name\" : \"Wasser\", \"amount\" : \"\"}, {\"name\" : \"Brühwürfel\", \"amount\" : \"1\"}, {\"name\" : \"Nudeln\", \"amount\" : \"200\"}]";
		Response response = recipeCollection.searchRecipe(invalidSearchParameter);
		assertEquals(400, response.getStatus());
	}

	@Test
	public void testDeleteRecipeValidParameters() {
		int validParameter = 1;
		Response response = recipeCollection.removeRecipe(validParameter);
		assertEquals(200, response.getStatus());
	}
	
	@Test
	public void testDeleteRecipeInvalidParameters() {
		int invalidParameter = 0;
		Response response = recipeCollection.removeRecipe(invalidParameter);
		assertEquals(400, response.getStatus());
	}
	
	@Test
	public void testListRecipes() {
		Response response = recipeCollection.listRecipes();
		assertEquals(200, response.getStatus());
	}
	
	@Test
	public void testListIcons() {
		Response response = laundryController.listIcons();
		assertEquals(200, response.getStatus());
	}

}
