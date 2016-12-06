package dhbw.pluto.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import dhbw.pluto.controller.exception.IconLoadingException;
import dhbw.pluto.model.LaundryIcon;
import dhbw.pluto.model.LaundryIconCollection;

import dhbw.pluto.database.strings.*;

public class LaundryIconDBHandler {

	public static LaundryIconCollection loadLaundryIcons() throws IconLoadingException {
		
		LaundryIconCollection laundryIcons = new LaundryIconCollection();
		
		try {
			Class.forName("org.sqlite.JDBC");
			Connection connection = DriverManager.getConnection("jdbc:sqlite:" + Tables.DB);
			Statement statement = connection.createStatement();
			
			ResultSet set = statement.executeQuery("SELECT * FROM " + Tables.LAUNDRY_ICON_VIEW + ";");
			while(set.next()) {
				String iconUrl = set.getString(Fields.URL);
				String iconDescription = set.getString(Fields.DESCRIPTION);
				String iconName = set.getString(Fields.NAME);
				String[] tags = set.getString(Tables.TAGS).split(",");
				LaundryIcon currentItem = new LaundryIcon(iconUrl, iconDescription, iconName, tags);
				laundryIcons.add(currentItem);
			}
	
			set.close();
			statement.close();
			connection.close();
		} catch (Exception e) {
			throw new IconLoadingException("The icons could not be loaded from the database. Reason: " + e.getMessage());
		}
		
		return laundryIcons;
	}
}
