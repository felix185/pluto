package dhbw.pluto.model.actvities;

public class RecipeCreationActivity extends Activity {

	public RecipeCreationActivity(String author, long timestamp, String recipeTitle) {
		super(author, timestamp);
		this.message = "hat das Rezept \"" + recipeTitle + "\" erstellt.";
	}

}
