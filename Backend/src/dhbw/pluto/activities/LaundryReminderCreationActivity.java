package dhbw.pluto.activities;

public class LaundryReminderCreationActivity extends Activity {
	
	public LaundryReminderCreationActivity(long timestamp, String author, String notificationTime) {
		super(author, timestamp);	
		this.message = "hat sich eine W�scheerinnerung f�r " + notificationTime + " gestellt.";
	}
}
