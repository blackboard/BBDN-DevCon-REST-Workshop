package bbdn.rest.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Availability duration of Learn objects as specified in the REST APIs
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Duration {

	/**
	 * Type setting. This can be set to 'Continuous', 'DateRange', or 'FixedNumDays' @see bbdn.rest.BbDef.Duration.
	 *
	 */
	@JsonProperty("type")
  private String type;

	/**
	 * Start date and time setting. Only used if type is DateRange.
	 *
	 */
	@JsonProperty("start")
  private String start;

	/**
	 * End date and time setting. Only used if type is DateRange.
	 *
	 */
	@JsonProperty("end")
  private String end;

	/**
	 * Empty constructor
	 */
	public Duration() {

	}

	/**
	 * Get the current type settings
	 * @return type
	 */
  public String getType() {
		return type;
	}

	/**
	 * Set type settings
	 * @param type: The type setting
	 */
	public void setType(String type) {
		this.type = type;
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
		return "Duration [type=" + type +
		 				", start=" + start +
						", end=" + end + "]";
	}

}
