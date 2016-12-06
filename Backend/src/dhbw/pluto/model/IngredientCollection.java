package dhbw.pluto.model;

import java.util.ArrayList;

import org.json.JSONArray;

public class IngredientCollection extends ArrayList<Ingredient> {

	public JSONArray toJSON() {
		JSONArray result = new JSONArray();
		for (int i = 0; i < super.size(); i++) {
			result.put(super.get(i).toJSON());
		}
		return result;
	}
	
	
	/**
	 * Fills an IngredientCollection from a String.
	 * @param ingredients The string specifying the ingredients seperated by commas and semicolons, e.g. "Sugar,500;Milk,200"
	 */
	public void fillFromString(String ingredients) {
		String[] ingredientArray = ingredients.split(";");
		for (int i = 0; i < ingredientArray.length; i++) {
      	  String[] currIngr = ingredientArray[i].split(",");
      	  String name = currIngr[0];
      	  String amount = currIngr[1];
      	  
      	  Ingredient currentIngredient = new Ingredient(name, amount);
      	  super.add(currentIngredient);
        }
	}
}
