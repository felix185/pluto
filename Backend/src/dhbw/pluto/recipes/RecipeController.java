package dhbw.pluto.recipes;

import java.sql.*;

public class RecipeController {

	//create
	
	//show
	public void showRecipes() {
		Connection c = null;
		Statement stmt = null;
		try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite:C:/Users/rieb/Documents/Semester 5/Software Engineering/pluto/Backend/src/dhbw/pluto/recipes/recipecollection.db");
		      c.setAutoCommit(false);
		      System.out.println("Opened database successfully");

		      stmt = c.createStatement();
		      ResultSet rs = stmt.executeQuery( "SELECT * FROM Recipes;" );
		      while ( rs.next() ) {
		          //int id = rs.getInt("id");
		          String  title = rs.getString("title");
		          String author  = rs.getString("author");
		          String  text = rs.getString("text");
		          Recipe recipe = new Recipe(title, author, text);
		          System.out.println(recipe);
		       }
		       rs.close();
		       stmt.close();
		       c.close();
		     } catch ( Exception e ) {
		       System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		       System.exit(0);
		     }
		     System.out.println("Operation done successfully");
	}
	//delete
	
}
