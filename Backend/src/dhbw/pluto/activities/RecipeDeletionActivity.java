package dhbw.pluto.activities;

public class RecipeDeletionActivity extends Activity {
	
	public RecipeDeletionActivity(long timestamp, String recipeTitle) {
		super("Jemand", timestamp);
		this.message = "hat das Rezept \"" + recipeTitle + "\" gelöscht.";
	}
}
