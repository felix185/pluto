package dhbw.pluto.model.actvities;

import java.util.List;

import dhbw.pluto.model.Ingredient;

public class RecipeSearchActivity extends Activity {

	public RecipeSearchActivity(long timestamp, List<Ingredient> ingredients) {
		super("Jemand", timestamp);
		buildMessage(ingredients);		
	}
	
	private void buildMessage(List<Ingredient> ingredients) {
		int countRemaining = ingredients.size();
		String namedIngredients = "";
		String remainingIngredients;
		/*
		 * TODO: Build a short List with Ingredients like
		 * "XY hat nach einem Rezept mit 500g Mehl, 125 Zucker, 1 Liter Milch und 5 weiteren gesucht."
		 */
		for(int i = 0; i < 3 && i < ingredients.size(); i++) {
			namedIngredients += ingredients.get(i).getAmount() + " " + ingredients.get(i).getName() + ", ";
			countRemaining--;
		}
		
		switch(countRemaining) {
		case 0:		remainingIngredients = "";
					break;
		case 1: 	remainingIngredients = "und einer weiteren Zutat";
					break;
		default:	remainingIngredients = "und " + String.valueOf(countRemaining) + " weiteren Zutaten";
					break;
		}
		this.message = "hat nach einem Rezept mit " + namedIngredients + remainingIngredients + " gesucht.";	
	}
}
