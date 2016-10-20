package dhbw.pluto.recipes;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import dhbw.pluto.recipes.Recipe;
import dhbw.pluto.recipes.Ingredient;

public class RecipeController {

	//create
	
	//show
	public static List<Recipe> showRecipes() throws RecipeLoadingException {
		Connection connection = null;
		Statement statement = null;
		
		List<Recipe> recipes = new ArrayList<>();
		List<Ingredient> ingredients = new ArrayList<>();
		
		try {
		      Class.forName("org.sqlite.JDBC");
		      connection = DriverManager.getConnection("jdbc:sqlite:C:/Users/rieb/Documents/Semester 5/Software Engineering/pluto/Backend/src/dhbw/pluto/recipes/recipecollection.db");
		      connection.setAutoCommit(false);

		      statement = connection.createStatement();
		      ResultSet rs = statement.executeQuery( "SELECT * FROM RecipeView;" );
		      while ( rs.next() ) {
		    	  int id = rs.getInt("id");
		          String  title = rs.getString("title");
		          String author  = rs.getString("author");
		          String  text = rs.getString("text");
		          
		          String[] ingredient = rs.getString("ingredients").split(";");
		          for (int i = 0; i < ingredient.length; i++) {
		        	  String[] currIngr = ingredient[i].split(",");
		        	  String name = currIngr[0];
		        	  String amount = currIngr[1];
		        	  
		        	  Ingredient currentIngredient = new Ingredient(name, amount);
		        	  ingredients.add(currentIngredient);
		          }
		          
		          Recipe currentRecipe = new Recipe(id, title, author, text, ingredients);
		          ingredients = new ArrayList<>();
		          recipes.add(currentRecipe);
		       }
		       rs.close();
		       statement.close();
		       connection.close();
		     } catch ( Exception e ) {
		    	 throw new RecipeLoadingException("The recipes could not be loaded from the database. Reason: " + e.getMessage());
		     }
		return recipes;
	}
	//delete
	
}
