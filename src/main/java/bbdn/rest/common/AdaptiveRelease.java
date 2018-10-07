package bbdn.rest.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * AdaptiveRelease of Learn objects as specified in the REST APIs
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdaptiveRelease {

	/**
	 * Start setting. This can be set to 'Yes' or 'No' in all cases. Some objects allow for different settings like 'Disabled' or 'Term'
	 */
	@JsonProperty("start")
	@JsonInclude(Include.NON_NULL)
  private String start;

	/**
	 * End setting. @see End.
	 */
	@JsonProperty("end")
	@JsonInclude(Include.NON_NULL)
  private String end;

	/**
	 * Empty constructor
	 */
	public AdaptiveRelease() {

	}

	/**
	 * Get the current start settings
	 * @return start
	 */
  public String getStart() {
		return start;
	}

	/**
	 * Set start settings
	 * @param start: The start setting
	 */
	public void setStart(String start) {
		this.start = start;
	}

	/**
	 * Get the current end settings
	 * @return end
	 */
  public String getEnd() {
		return end;
	}

	/**
	 * Set end settings
	 * @param end: The end setting
	 */
	public void setEnd(String end) {
		this.end = end;
	}

	@Override
	public String toString() {
    String start = this.start != null ? this.start : "null";
		String end = this.end != null ? this.end : "null";
		return "AdaptiveRelease [start=" + start +
					 ", end" + end + "]";
	}

}
