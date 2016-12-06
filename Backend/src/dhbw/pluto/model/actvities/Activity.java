package dhbw.pluto.model.actvities;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Superclass for all activities that are recognized by the activity logger.
 * @author Felix Seidel
 *
 */
public class Activity implements Comparable {
	
	protected String author;
	protected String message;
	protected long timestamp;
	
	/**
	 * Constructor to be used by any inheriting class. The extending class sets an individual message by itself.
	 * @param author The eMail address of the author/trigger of an activity.
	 * @param timestamp The time when the activity happened.
	 */
	protected Activity(String author, long timestamp) {
		this.author = author;
		this.timestamp = timestamp;
	}
	
	/**
	 * Constructor to be used in any unspecialized case. If the message is used multiple times, you should consider writing an own
	 * activity class.
	 * @param author The eMail address of the author/trigger of an activity.
	 * @param timestamp The time when the activity happend.
	 * @param message The message of the activity.
	 */
	public Activity(String author, long timestamp, String message) {
		this.author = author;
		this.timestamp = timestamp;
		this.message = message;
	}
	
	/**
	 * Convert the activity into valid JSON.
	 * @return
	 */
	public final JSONObject toJSON() {
		JSONObject result = new JSONObject();
		
		try {
			result.put("activity", message);
			result.put("timestamp", timestamp);
			result.put("eMail", author);
		} catch (JSONException e) {
			// put() throws e, if key==null. Since key=<STRING>, this exception should never be thrown.
			e.printStackTrace();
		}
		
		return result;
	}
	
	/*
	 *		GETTER
	 */
	public String getActivity() {
		return message;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public long getTimestamp() {
		return timestamp;
	}

	@Override
	public int compareTo(Object o) {
		Activity a = (Activity) o;
		return Long.compare(a.getTimestamp(), this.timestamp);
	}
}
