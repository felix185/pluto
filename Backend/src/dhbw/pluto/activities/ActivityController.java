package dhbw.pluto.activities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ActivityController {

	public ActivityController() {
		
	}
	
	public static void writeActivity(Activity activity) throws ActivityCreationException {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection connection = DriverManager.getConnection("jdbc:sqlite:pluto.db");
			PreparedStatement statement = connection.prepareStatement("INSERT INTO activities (description, eMail, timestamp) VALUES (?, ?, ?);");
			statement.setString(1, activity.getActivity());
			statement.setString(2, activity.getAuthor());
			statement.setLong(3, activity.getTimestamp());
			statement.execute();
			statement.close();
			connection.close();
		} catch (Exception e) {
			throw new ActivityCreationException("The activity could not be written into the database. Reason: " + e.getMessage());
		}
	}
	

	public static List<Activity> getActivities() throws ActivityLoadingException {
		List<Activity> result = new ArrayList<>();
		try {
			Class.forName("org.sqlite.JDBC");
			Connection connection = DriverManager.getConnection("jdbc:sqlite:pluto.db");
			Statement statement = connection.createStatement();
			
			ResultSet set = statement.executeQuery("SELECT * FROM activities;");
			while(set.next()) {
				Activity currentActivity = new Activity(set.getString("eMail"), set.getLong("timestamp"), set.getString("description"));
				result.add(currentActivity);
			}

			set.close();
			statement.close();
			connection.close();
		} catch (Exception e) {
			throw new ActivityLoadingException("The activities could not be loaded from the database. Reason: " + e.getMessage());
		}
		return result;
	}
}
