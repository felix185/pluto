package dhbw.pluto.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import dhbw.pluto.controller.exception.ActivityCreationException;
import dhbw.pluto.controller.exception.ActivityLoadingException;
import dhbw.pluto.database.strings.Fields;
import dhbw.pluto.database.strings.Tables;
import dhbw.pluto.model.ActivityCollection;
import dhbw.pluto.model.actvities.Activity;


public class ActivityDBHandler {

	public ActivityDBHandler() {
		
	}
	
	public static void writeActivity(Activity activity) throws ActivityCreationException {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection connection = DriverManager.getConnection("jdbc:sqlite:" + Tables.DB);
			PreparedStatement statement = connection.prepareStatement("INSERT INTO " + Tables.ACTIVITIES + " (" + Fields.DESCRIPTION +"," + Fields.EMAIL + "," + Fields.TIMESTAMP + ") VALUES (?, ?, ?);");
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
	

	public static ActivityCollection getActivities() throws ActivityLoadingException {
		ActivityCollection result = new ActivityCollection();
		try {
			Class.forName("org.sqlite.JDBC");
			Connection connection = DriverManager.getConnection("jdbc:sqlite:" + Tables.DB);
			Statement statement = connection.createStatement();
			
			ResultSet set = statement.executeQuery("SELECT * FROM " + Tables.ACTIVITIES + ";");
			while(set.next()) {
				Activity currentActivity = new Activity(set.getString(Fields.EMAIL), set.getLong(Fields.TIMESTAMP), set.getString(Fields.DESCRIPTION));
				result.add(currentActivity);
			}

			set.close();
			statement.close();
			connection.close();
		} catch (Exception e) {
			throw new ActivityLoadingException("The activities could not be loaded from the database. Reason: " + e.getMessage());
		}
		result.sort(null);
		return result;
	}
}
