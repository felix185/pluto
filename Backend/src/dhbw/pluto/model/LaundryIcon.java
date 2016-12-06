package dhbw.pluto.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Class to represent a laundry information icon
 * @author Felix Seidel
 *
 */
public class LaundryIcon {
	
	private String imageURL;
	private String description;
	private String name;
	private String[] tags;

	/**
	 * Creates a new Laundry Icon
	 * @param URL
	 * @param description
	 * @param name
	 * @param tags
	 */
	public LaundryIcon(String URL, String description, String name, String[] tags) {
		this.imageURL = URL;
		this.description = description;
		this.name = name;
		this.tags = tags;
	}
	
	/**
	 * Converts the icon into valid JSON.
	 * @return
	 */
	public JSONObject toJSON() {
		JSONObject result = new JSONObject();
		try {
			result.put("name", name);
			result.put("url", imageURL);
			result.put("description", description);
			result.put("tags", tags);
		} catch (JSONException e) {
			// put() throws e, if key==null. Since key=<STRING>, this exception should never be thrown.
			e.printStackTrace();
		}
		return result;
	}
}
