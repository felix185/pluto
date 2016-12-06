package dhbw.pluto.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Recipe {
	
	private int id;
	private String title;
	private String author;
	private String text;
	IngredientCollection ingredients;
	private int score = 100;
	
	/*
	 * Constructor
	 */
	
	public Recipe (int id, String title, String author, String text, IngredientCollection ingredients) {
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
	        result.put("score", score);
	        JSONArray ingredientsArray = ingredients.toJSON();
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
	
	public void setScore(int score) {
		this.score = score;
	}

}

	