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
import dhbw.pluto.model.RecipeCollection;

public class RecipeDBHandler {

	//create
	public static void createRecipe(String title, String text, String author, IngredientCollection ingredients) throws RecipeCreationException {
		Recipe recipe;
		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:pluto.db");
			connection.setAutoCommit(false);
			
			statement = connection.prepareStatement("INSERT INTO Recipes (title, author, text) VALUES (?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
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
			PreparedStatement ingredientCheck = connection.prepareStatement("INSERT INTO Ingredients (name) SELECT ? WHERE NOT EXISTS (SELECT 1 FROM Ingredients WHERE name = ?);");
			PreparedStatement ingredientMatch = connection.prepareStatement("INSERT INTO recipes_ingredients (recipe_id, ingredient_id, amount) SELECT ?, id, ? FROM Ingredients WHERE name = ?;");
			
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
		IngredientCollection ingredients = new IngredientCollection();
		
		try {
		      Class.forName("org.sqlite.JDBC");
		      connection = DriverManager.getConnection("jdbc:sqlite:pluto.db");
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
		          ingredients = new IngredientCollection();
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
	

	
	public static RecipeCollection searchRecipes(IngredientCollection givenIngredients) throws RecipeLoadingException {
		RecipeCollection recipes = new RecipeCollection();
		IngredientCollection ingredients = new IngredientCollection();
		
		try {
		      Class.forName("org.sqlite.JDBC");
		      Connection connection = DriverManager.getConnection("jdbc:sqlite:pluto.db");
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
		          ingredients = new IngredientCollection();
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
		String baseQuery = "SELECT * FROM RecipeView " +
							"WHERE NOT EXISTS " +
							"( "+
							"SELECT * FROM recipes_ingredients LEFT JOIN " +
							"( " +
							"SELECT name AS ingredientName, id AS ingredientId FROM Ingredients " +
							"WHERE name IN (?";
		
		for(int i = 1; i < countIngredients; i++) {
			baseQuery += ",?";
		}
		
		baseQuery += ") "+
						") " +  
						"ON ingredientId = recipes_ingredients.ingredient_id " + 
						"WHERE ingredientName IS NULL AND RecipeView.id = recipes_ingredients.recipe_id " + 
						");";
		return baseQuery;
	}
	

	//delete
	public static void deleteRecipe(int id) throws RecipeDeletionException {
		Connection connection = null;
		
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:pluto.db");
			connection.setAutoCommit(false);
			
			PreparedStatement recipeDelete = connection.prepareStatement("DELETE FROM Recipes WHERE id = ?;");
			PreparedStatement relationDelete = connection.prepareStatement("DELETE FROM recipes_ingredients WHERE recipe_id = ?;");
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
