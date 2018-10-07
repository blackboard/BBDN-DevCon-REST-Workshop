package bbdn.rest.assignment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Availability duration of Learn objects as specified in the REST APIs
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Score {

	/**
	 * Start date and time setting. Only used if type is DateRange.
	 *
	 */
	@JsonProperty("possible")
  private int possible;


	/**
	 * Empty constructor
	 */
	public Score() {

	}

	/**
	* Returns value of attemptsAllowed
	* @return
	*/
	public int getPossible() {
		return possible;
	}

	/**
	* Sets new value of attemptsAllowed
	* @param
	*/
	public void setPossible(int possible) {
		this.possible = possible;
	}


	/**
	* Create string representation of Duration for printing
	* @return
	*/
	@Override
	public String toString() {
		return "Score [possible=" + possible + "]";
	}
}
