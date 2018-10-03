package bbdn.rest.content;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * ContentHandler of Learn objects as specified in the REST APIs
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContentHandler {

	/**
	 * Start setting. This can be set to 'Yes' or 'No' in all cases. Some objects allow for different settings like 'Disabled' or 'Term'
	 */
	@JsonProperty("id")
  private String id;

	/**
	 * Empty constructor
	 */
	public ContentHandler() {
		super();
	}

	/**
	 * Get the current start settings
	 * @return start
	 */
  public String getId() {
		return id;
	}

	/**
	 * Set start settings
	 * @param start: The start setting
	 */
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
    return "ContentHandler [id=" + id + "]";
	}

}
