package dhbw.pluto.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dhbw.pluto.controller.exception.RecipeCreationException;
import dhbw.pluto.controller.exception.RecipeDeletionException;
import dhbw.pluto.controller.exception.RecipeLoadingException;
import dhbw.pluto.model.Ingredient;
import dhbw.pluto.model.IngredientCollection;
import dhbw.pluto.model.Recipe;
import dhbw.pluto.database.strings.*;
import dhbw.pluto.model.RecipeCollection;

public class RecipeDBHandler {

	//create
	public static void createRecipe(String title, String text, String author, IngredientCollection ingredients) throws RecipeCreationException {
		Recipe recipe;
		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:" + Tables.DB);
			connection.setAutoCommit(false);
			
			statement = connection.prepareStatement("INSERT INTO " + Tables.RECIPES + " (" + Fields.TITLE + ", " + Fields.AUTHOR + ", " + Fields.TEXT + ") VALUES (?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
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
			PreparedStatement ingredientMatch = connection.prepareStatement("INSERT INTO " + Tables.RECIPE_INGREDIENTS + " (" + Fields.RECIPE_ID + ", " + Fields.INGREDIENT_ID + ", " + Fields.AMOUNT + ") SELECT ?, id, ? FROM " + Tables.INGREDIENTS + " WHERE name = ?;");
			
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
	public static RecipeCollection showRecipes() throws RecipeLoadingException {
		Connection connection = null;
		Statement statement = null;
		
		RecipeCollection recipes = new RecipeCollection();
		
		try {
		      Class.forName("org.sqlite.JDBC");
		      connection = DriverManager.getConnection("jdbc:sqlite:" + Tables.DB);
		      connection.setAutoCommit(false);

		      statement = connection.createStatement();
		      ResultSet rs = statement.executeQuery( "SELECT * FROM " + Tables.RECIPE_VIEW + ";" );
		      while (rs.next()) {
		          recipes.add(createRecipeFromResultSet(rs));
		       }
		       rs.close();
		       statement.close();
		       connection.close();
		     } catch ( Exception e ) {
		    	 throw new RecipeLoadingException("The recipes could not be loaded from the database. Reason: " + e.getMessage());
		     }
		return recipes;
	}
	
	
	public static RecipeCollection searchRecipes(IngredientCollection givenIngredients) throws RecipeLoadingException {
		RecipeCollection recipes = new RecipeCollection();
		
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
		    	  recipes.add(createRecipeFromResultSet(rs));
		      }
		       rs.close();
		       statement.close();
		      connection.close();
		     
		} catch ( Exception e ) {
		   	 throw new RecipeLoadingException("The recipes could not be loaded from the database. Reason: " + e.getMessage());
		}
		
		return recipes;
	}
	
	private static Recipe createRecipeFromResultSet(ResultSet rs) throws SQLException {
		IngredientCollection ingredients = new IngredientCollection();
		int id = rs.getInt(Fields.ID);
        String  title = rs.getString(Fields.TITLE);
        String author  = rs.getString(Fields.AUTHOR);
        String text = rs.getString(Fields.TEXT);		          
        String ingredient = rs.getString(Fields.INGREDIENTS);        
        
        ingredients.fillFromString(ingredient);
        
        return new Recipe(id, title, author, text, ingredients);		
	}
	
	private static String buildSearchQuery(int countIngredients) {
		String baseQuery = "SELECT * FROM " + Tables.RECIPE_VIEW + " " +
							"WHERE NOT EXISTS " +
							"( "+
							"SELECT * FROM " + Tables.RECIPE_INGREDIENTS + " LEFT JOIN " +
							"( " +
							"SELECT " + Fields.NAME + " AS ingredientName, " + Fields.ID + " AS ingredientId FROM " + Tables.INGREDIENTS + " " +
							"WHERE " + Fields.NAME + " IN (?";
		
		for(int i = 1; i < countIngredients; i++) {
			baseQuery += ",?";
		}
		
		baseQuery += ") "+
						") " +  
						"ON ingredientId = " + Tables.RECIPE_INGREDIENTS + "." + Fields.INGREDIENT_ID + " " + 
						"WHERE ingredientName IS NULL AND " + Tables.RECIPE_VIEW + ".id = " + Tables.RECIPE_INGREDIENTS + "." + Fields.RECIPE_ID + " " + 
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
			
			PreparedStatement recipeDelete = connection.prepareStatement("DELETE FROM " + Tables.RECIPES + " WHERE " + Fields.ID + " = ?;");
			PreparedStatement relationDelete = connection.prepareStatement("DELETE FROM " + Tables.RECIPE_INGREDIENTS + " WHERE " + Fields.RECIPE_ID + " = ?;");
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
