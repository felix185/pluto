package dhbw.pluto.recipes;

import java.util.List;

import org.json.*;

public class Recipe {
	
	private int id;
	private String title;
	private String author;
	private String text;
	List<Ingredient> ingredients;
	
	/*
	 * Constructor
	 */
	
	public Recipe (int id, String title, String author, String text, List<Ingredient> ingredients) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.text = text;
		this.ingredients = ingredients;
	}
	
	//generate valid JSON
	public JSONObject toJSON() {
	    JSONObject result = new JSONObject();

	    try {
	    	result.put("id", id);
	        result.put("title", title);
	        result.put("text", text);
	        result.put("eMail", author);
	        JSONArray ingredientsArray = new JSONArray();
	        for(int i = 0; i < ingredients.size(); i++) {
	        	ingredientsArray.put(ingredients.get(i).toJSON());
	        }
	        result.put("ingredients", ingredientsArray);
	    } catch (JSONException e) {
	        // put() throws e, if key==null. Since key=<STRING>, this exception should never be thrown.
	        e.printStackTrace();
	    }

	    return result;
	}
	
	public int getId() {
		return id;
	}

}

	