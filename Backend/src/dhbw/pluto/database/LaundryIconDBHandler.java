package dhbw.pluto.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import dhbw.pluto.controller.exception.IconLoadingException;
import dhbw.pluto.model.LaundryIcon;
import dhbw.pluto.model.LaundryIconCollection;

public class LaundryIconDBHandler {

	public static LaundryIconCollection loadLaundyIcons() throws IconLoadingException {
		
		LaundryIconCollection laundryIcons = new LaundryIconCollection();
		
		try {
			Class.forName("org.sqlite.JDBC");
			Connection connection = DriverManager.getConnection("jdbc:sqlite:pluto.db");
			Statement statement = connection.createStatement();
			
			ResultSet set = statement.executeQuery("SELECT * FROM laundryIconView;");
			while(set.next()) {
				String iconUrl = set.getString("url");
				String iconDescription = set.getString("description");
				String iconName = set.getString("name");
				String[] tags = set.getString("tags").split(",");
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
