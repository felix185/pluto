package dhbw.pluto.activities;

public class RecipeCreationActivity extends Activity {

	public RecipeCreationActivity(String author, long timestamp, String recipeTitle) {
		super(author, timestamp);
		this.message = "hat das Rezept \"" + recipeTitle + "\" erstellt.";
	}

}
