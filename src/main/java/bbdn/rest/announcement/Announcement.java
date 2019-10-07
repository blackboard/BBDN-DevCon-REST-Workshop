package bbdn.rest.announcement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import bbdn.rest.BaseObject;
import bbdn.rest.common.Availability;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Announcement extends BaseObject {

	@JsonProperty("id")
  	private String id;

	@JsonProperty("title")
	private String title;

	@JsonProperty("body")
	private String body;

	@JsonProperty("availability")
	private Availability availability;

	public Announcement() {
		super();
  }


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
	* Returns value of title
	* @return
	*/
	public String getTitle() {
		return title;
	}

	/**
	* Sets new value of title
	* @param
	*/
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	* Returns value of body
	* @return
	*/
	public String getBody() {
		return body;
	}

	/**
	* Sets new value of body
	* @param
	*/
	public void setBody(String body) {
		this.body = body;
	}

	/**
	* Returns value of availability
	* @return
	*/
	//public AnnouncementAvailability getAvailability() {
	public Availability getAvailability() {
		return availability;
	}

	/**
	* Sets new value of availability
	* @param
	*/
	//public void setAvailability(AnnouncementAvailability availability) {
	public void setAvailability(Availability availability) {
		this.availability = availability;
	}

	


	/**
	* Create string representation of Announcement for printing
	* @return
	*/
	@Override
	public String toString() {
		String avl = availability != null ? availability.toString() : "null";
		
		return "Announcement [id=" + id + ", title=" + title + ", body=" + body + ", availability=" + availability +  ", " + super.toString() + "]";
	}
}
