package bbdn.rest.course;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;


/**
 * Locale is an object for storing localization information for courses.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Locale {

	/**
	 * Locale ID - The locale of this course
	 */
	@JsonProperty("id")
  private String id;

	/**
	 * Force - Whether students are forced to use the course's specified locale
	 */
	@JsonProperty("force")
  private boolean force;

	/**
	* Returns value of id
	* @return
	*/
	public String getId() {
		return id;
	}

	/**
	* Sets new value of id
	* @param
	*/
	public void setId(String id) {
		this.id = id;
	}

	/**
	* Returns value of force
	* @return
	*/
	public boolean isForce() {
		return force;
	}

	/**
	* Sets new value of force
	* @param
	*/
	public void setForce(boolean force) {
		this.force = force;
	}

	/**
	* Create string representation of Locale for printing
	* @return
	*/
	@Override
	public String toString() {
		return "Locale [id=" + id + ", force=" + force + "]";
	}

	/**
	* Default empty Locale constructor
	*/
	public Locale() {
		super();
	}

	/**
	* Default Locale constructor
	*/
	public Locale(String id, boolean force) {
		super();
		this.id = id;
		this.force = force;
	}
}
