package dhbw.pluto.activities;

public class RecipeDeletionActivity extends Activity {
	
	public RecipeDeletionActivity(long timestamp, long recipeId) {
		super("Jemand", timestamp);
		this.message = "hat das Rezept #" + String.valueOf(recipeId) + " gelöscht.";
	}
}
