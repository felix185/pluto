package dhbw.pluto.activities;

public class LaundryNotificationActivity extends Activity {

	public LaundryNotificationActivity(long timestamp, String author) {
		super(author, timestamp);	
		this.message = "hat gerade eine Wäscheerinnerung erhalten.";
	}
}
