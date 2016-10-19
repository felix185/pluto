package dhbw.pluto.activities;

public class LaundryReminderCreationActivity extends Activity {
	
	public LaundryReminderCreationActivity(long timestamp, String author, String notificationTime) {
		super(author, timestamp);	
		this.message = "hat sich eine Wäscheerinnerung für " + notificationTime + " gestellt.";
	}
}
