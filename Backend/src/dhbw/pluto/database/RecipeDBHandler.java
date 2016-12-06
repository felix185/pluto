package dhbw.pluto.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import dhbw.pluto.controller.exception.RecipeCreationException;
import dhbw.pluto.controller.exception.RecipeDeletionException;
import dhbw.pluto.controller.exception.RecipeLoadingException;
import dhbw.pluto.model.Ingredient;
import dhbw.pluto.model.Recipe;
import dhbw.pluto.database.strings.*;

public class RecipeDBHandler {

	//create
	public static void createRecipe(String title, String text, String author, List<Ingredient> ingredients) throws RecipeCreationException {
		Recipe recipe;
		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:" + Tables.DB);
			connection.setAutoCommit(false);
			
			statement = connection.prepareStatement("INSERT INTO " + Tables.RECIPES + " (title, author, text) VALUES (?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, title);
			statement.setString(2,  author);
			statement.setString(3, text);
			statement.execute();
			ResultSet generatedKeys = statement.getGeneratedKeys();
			if (generatedKeys.next()) {
				recipe = new Recipe(generatedKeys.getInt(1), title, text, author, ingredients);
			} else {
				throw new SQLException("Creating recipe failed, no ID obtained");
			}
			PreparedStatement ingredientCheck = connection.prepareStatement("INSERT INTO " + Tables.INGREDIENTS + " (name) SELECT ? WHERE NOT EXISTS (SELECT 1 FROM "+ Tables.INGREDIENTS +" WHERE name = ?);");
			PreparedStatement ingredientMatch = connection.prepareStatement("INSERT INTO " + Tables.RECIPE_INGREDIENTS + " (recipe_id, ingredient_id, amount) SELECT ?, id, ? FROM " + Tables.INGREDIENTS + " WHERE name = ?;");
			
			for(Ingredient currentIngredient : ingredients) {
				ingredientCheck.setString(1, currentIngredient.getName());
				ingredientCheck.setString(2, currentIngredient.getName());
				ingredientCheck.addBatch();
				
				ingredientMatch.setInt(1, recipe.getId());
				ingredientMatch.setString(2, currentIngredient.getAmount());
				ingredientMatch.setString(3, currentIngredient.getName());
				ingredientMatch.addBatch();
			}
			ingredientCheck.executeBatch();
			ingredientMatch.executeBatch();
			
			connection.commit();
			generatedKeys.close();
			statement.close();
			ingredientCheck.close();
			ingredientMatch.close();
			connection.close();
			
		} catch ( SQLException e ) {
	    	 throw new RecipeCreationException("The recipes could not be entered to the database. Reason: " + e.getMessage());
	     }
		catch ( Exception e ) {
			e.printStackTrace();
	    	 throw new RecipeCreationException("The recipes could not be entered to the database. Reason (Ex): " + e.getMessage());
	     }
	}
	
	//show
	public static List<Recipe> showRecipes() throws RecipeLoadingException {
		Connection connection = null;
		Statement statement = null;
		
		List<Recipe> recipes = new ArrayList<>();
		List<Ingredient> ingredients = new ArrayList<>();
		
		try {
		      Class.forName("org.sqlite.JDBC");
		      connection = DriverManager.getConnection("jdbc:sqlite:" + Tables.DB);
		      connection.setAutoCommit(false);

		      statement = connection.createStatement();
		      ResultSet rs = statement.executeQuery( "SELECT * FROM " + Tables.RECIPE_VIEW + ";" );
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
	

	
	public static List<Recipe> searchRecipes(List<Ingredient> givenIngredients) throws RecipeLoadingException {
		List<Recipe> recipes = new ArrayList<>();
		List<Ingredient> ingredients = new ArrayList<>();
		
		try {
		      Class.forName("org.sqlite.JDBC");
		      Connection connection = DriverManager.getConnection("jdbc:sqlite:" + Tables.DB);
		      connection.setAutoCommit(false);

		      PreparedStatement statement = connection.prepareStatement(buildSearchQuery(givenIngredients.size()));
		      int j = 1;
		      for(Ingredient currentIngredient : givenIngredients) {
		    	  statement.setString(j++, currentIngredient.getName());
		      }
		      
		      ResultSet rs = statement.executeQuery();
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
	
	private static String buildSearchQuery(int countIngredients) {
		String baseQuery = "SELECT * FROM " + Tables.RECIPE_VIEW + " " +
							"WHERE NOT EXISTS " +
							"( "+
							"SELECT * FROM " + Tables.RECIPE_INGREDIENTS + " LEFT JOIN " +
							"( " +
							"SELECT name AS ingredientName, id AS ingredientId FROM " + Tables.INGREDIENTS + " " +
							"WHERE name IN (?";
		
		for(int i = 1; i < countIngredients; i++) {
			baseQuery += ",?";
		}
		
		baseQuery += ") "+
						") " +  
						"ON ingredientId = " + Tables.INGREDIENTS + ".ingredient_id " + 
						"WHERE ingredientName IS NULL AND " + Tables.RECIPE_VIEW + ".id = " + Tables.RECIPE_INGREDIENTS + ".recipe_id " + 
						");";
		return baseQuery;
	}
	

	//delete
	public static void deleteRecipe(int id) throws RecipeDeletionException {
		Connection connection = null;
		
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:" + Tables.DB);
			connection.setAutoCommit(false);
			
			PreparedStatement recipeDelete = connection.prepareStatement("DELETE FROM " + Tables.RECIPES + " WHERE id = ?;");
			PreparedStatement relationDelete = connection.prepareStatement("DELETE FROM " + Tables.RECIPE_INGREDIENTS + " WHERE recipe_id = ?;");
			recipeDelete.setInt(1, id);
			relationDelete.setInt(1, id);
			
			recipeDelete.execute();
			relationDelete.execute();
			
			connection.commit();
			recipeDelete.close();
			relationDelete.close();
			connection.close();
		} catch (Exception e) {
			throw new RecipeDeletionException("The recipe could not be deleted. Reason: " + e.getMessage());
		}
	}

}
