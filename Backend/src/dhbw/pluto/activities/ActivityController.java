package dhbw.pluto.activities;

import java.sql.*;

public class ActivityController {

	public ActivityController() {
		
	}
	
	public void writeActivity(Activity activity) throws ActivityCreationException
	{
		try {
			Class.forName("org.sqlite.JDBC");
			Connection connection = DriverManager.getConnection("jdbc:sqlite:pluto.db");
			PreparedStatement statement = connection.prepareStatement("INSERT INTO activities (description, eMail, timestamp) VALUES (?, ?, ?)");
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
	
	public Activity[] getActivities()
	{
		return null;
	}
}
