package dhbw.pluto.model.actvities;

public class RecipeDeletionActivity extends Activity {
	
	public RecipeDeletionActivity(long timestamp, long recipeId) {
		super("Jemand", timestamp);
		this.message = "hat das Rezept #" + String.valueOf(recipeId) + " gelöscht.";
	}
}
