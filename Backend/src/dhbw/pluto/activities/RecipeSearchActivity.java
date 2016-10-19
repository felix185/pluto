package dhbw.pluto.activities;

public class RecipeSearchActivity extends Activity {

	public RecipeSearchActivity(long timestamp) {
		super("Jemand", timestamp);
		buildMessage();		
	}
	
	private void buildMessage() {
		int countRemaining = 0;
		String namedIngredients = "";
		String remainingIngredients;
		/*
		 * TODO: Build a short List with Ingredients like
		 * "XY hat nach einem Rezept mit 500g Mehl, 125 Zucker, 1 Liter Milch und 5 weiteren gesucht."
		 */
		
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
