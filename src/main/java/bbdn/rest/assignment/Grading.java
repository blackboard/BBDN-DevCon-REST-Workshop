package bbdn.rest.assignment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Availability duration of Learn objects as specified in the REST APIs
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Grading {

	/**
	 * Type setting. This can be set to 'Continuous', 'DateRange', or 'FixedNumDays' @see bbdn.rest.BbDef.Duration.
	 *
	 */
	@JsonProperty("due")
  private String due;

	/**
	 * Start date and time setting. Only used if type is DateRange.
	 *
	 */
	@JsonProperty("attemptsAllowed")
  private int attemptsAllowed;

	/**
	 * End date and time setting. Only used if type is DateRange.
	 *
	 */
	@JsonProperty("gradeSchemaId")
	@JsonInclude(Include.NON_NULL)
  private String gradeSchemaId;

	/**
	 * daysOfUse setting. Only used if type is FixedNumDays.
	 *
	 */
	@JsonProperty("isUnlimitedAttemptsAllowed")
  private boolean isUnlimitedAttemptsAllowed;

	/**
	 * Empty constructor
	 */
	public Grading() {

	}




	/**
	* Returns value of due
	* @return
	*/
	public String getDue() {
		return due;
	}

	/**
	* Sets new value of due
	* @param
	*/
	public void setDue(String due) {
		this.due = due;
	}

	/**
	* Returns value of attemptsAllowed
	* @return
	*/
	public int getAttemptsAllowed() {
		return attemptsAllowed;
	}

	/**
	* Sets new value of attemptsAllowed
	* @param
	*/
	public void setAttemptsAllowed(int attemptsAllowed) {
		this.attemptsAllowed = attemptsAllowed;
	}

	/**
	* Returns value of gradeSchemaId
	* @return
	*/
	public String getGradeSchemaId() {
		return gradeSchemaId;
	}

	/**
	* Sets new value of gradeSchemaId
	* @param
	*/
	public void setGradeSchemaId(String gradeSchemaId) {
		this.gradeSchemaId = gradeSchemaId;
	}

	/**
	* Returns value of isUnlimitedAttemptsAllowed
	* @return
	*/
	public boolean isIsUnlimitedAttemptsAllowed() {
		return isUnlimitedAttemptsAllowed;
	}

	/**
	* Sets new value of isUnlimitedAttemptsAllowed
	* @param
	*/
	public void setIsUnlimitedAttemptsAllowed(boolean isUnlimitedAttemptsAllowed) {
		this.isUnlimitedAttemptsAllowed = isUnlimitedAttemptsAllowed;
	}


	/**
	* Create string representation of Duration for printing
	* @return
	*/
	@Override
	public String toString() {
		return "Grading [due=" + due + ", attemptsAllowed=" + attemptsAllowed + ", gradeSchemaId=" + gradeSchemaId + ", isUnlimitedAttemptsAllowed=" + isUnlimitedAttemptsAllowed + "]";
	}
}
