package bbdn.rest.course;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;


/**
 * Enrollment is an object for manipulating the enrollment type for courses.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Enrollment {

	/**
	 * Type - Specifies the enrollment options for the course. Defaults to InstructorLed. = ['InstructorLed', 'SelfEnrollment', 'EmailEnrollment']
	 * @see bbdn.rest.BbDef.CourseEnrollment
	 */
	@JsonProperty("type")
  private String type;

	/**
	 * Start - The date on which enrollments are allowed for the course. May only be set if enrollment.type is
	 * @see bbdn.rest.BbDef.CourseEnrollment.SELF
	 */
	@JsonProperty("start")
  private String start;

	/**
	 * End - The date on which enrollment in this course ends. May only be set if enrollment.type is
	 * @see bbdn.rest.BbDef.CourseEnrollment.SELF
	 */
	@JsonProperty("end")
  private String end;

	/**
	 * Access Code - The enrollment access code associated with this course. May only be set if enrollment.type is
	 * @see bbdn.rest.BbDef.CourseEnrollment.SELF
	 */
	@JsonProperty("accessCode")
  private String accessCode;

 /**
	* Create string representation of Enrollment for printing
	* @return
	*/
	@Override
	public String toString() {
		return "Enrollment [type=" + type + ", start=" + start + ", end=" + end + ", accessCode=" + accessCode + "]";
	}

	/**
	* Default empty Enrollment constructor
	*/
	public Enrollment() {
		super();
	}

	/**
	* Default Enrollment constructor
	*/
	public Enrollment(String type, String start, String end, String accessCode) {
		super();
		this.type = type;
		this.start = start;
		this.end = end;
		this.accessCode = accessCode;
	}

	/**
	* Returns value of type
	* @return
	*/
	public String getType() {
		return type;
	}

	/**
	* Sets new value of type
	* @param
	*/
	public void setType(String type) {
		this.type = type;
	}

	/**
	* Returns value of start
	* @return
	*/
	public String getStart() {
		return start;
	}

	/**
	* Sets new value of start
	* @param
	*/
	public void setStart(String start) {
		this.start = start;
	}

	/**
	* Returns value of end
	* @return
	*/
	public String getEnd() {
		return end;
	}

	/**
	* Sets new value of end
	* @param
	*/
	public void setEnd(String end) {
		this.end = end;
	}

	/**
	* Returns value of accessCode
	* @return
	*/
	public String getAccessCode() {
		return accessCode;
	}

	/**
	* Sets new value of accessCode
	* @param
	*/
	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}
}
