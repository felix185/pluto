package dhbw.pluto.recipes;

import org.json.*;

public class Recipe {
	
	protected String title;
	protected String author;
	protected String text;
	
	public Recipe (String title, String author, String text) {
		this.title = title;
		this.author = author;
		this.text = text;
	}
	
	//generate valid JSON
	public final JSONObject toJSON() {
	    JSONObject result = new JSONObject();

	    try {
	        result.put("title", title);
	        result.put("text", text);
	        result.put("eMail", author);
	    } catch (JSONException e) {
	        // put() throws e, if key==null. Since key=<STRING>, this exception should never be thrown.
	        e.printStackTrace();
	    }

	    return result;
	}
	
	public Recipe getRecipe(String title, String author, String text) {
		Recipe recipe = new Recipe(title, author, text);
		return recipe;
	}

}
