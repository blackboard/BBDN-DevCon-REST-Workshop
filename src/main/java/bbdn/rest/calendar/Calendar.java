package bbdn.rest.calendar;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import bbdn.rest.BaseObject;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Calendar extends BaseObject {

	@JsonProperty("id")
  private String id;

	@JsonProperty("type")
	private String type;

	@JsonProperty("calendarId")
  private String calendarId;

	@JsonProperty("calendarName")
	@JsonInclude(Include.NON_NULL)
  private String calendarName;

	@JsonProperty("title")
	private String title;

	@JsonProperty("description")
	private String description;

	@JsonProperty("start")
  private String start;

	@JsonProperty("end")
  private String end;

	@JsonProperty("createdByUserId")
	@JsonInclude(Include.NON_NULL)
  private String createdByUserId;

	public Calendar() {
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
	* Returns value of calendarId
	* @return
	*/
	public String getCalendarId() {
		return calendarId;
	}

	/**
	* Sets new value of calendarId
	* @param
	*/
	public void setCalendarId(String calendarId) {
		this.calendarId = calendarId;
	}

	/**
	* Returns value of calendarName
	* @return
	*/
	public String getCalendarName() {
		return calendarName;
	}

	/**
	* Sets new value of calendarName
	* @param
	*/
	public void setCalendarName(String calendarName) {
		this.calendarName = calendarName;
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
	* Returns value of description
	* @return
	*/
	public String getDescription() {
		return description;
	}

	/**
	* Sets new value of description
	* @param
	*/
	public void setDescription(String description) {
		this.description = description;
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
	* Returns value of createdByUserId
	* @return
	*/
	public String getCreatedByUserId() {
		return createdByUserId;
	}

	/**
	* Sets new value of createdByUserId
	* @param
	*/
	public void setCreatedByUserId(String createdByUserId) {
		this.createdByUserId = createdByUserId;
	}

	/**
	* Create string representation of Calendar for printing
	* @return
	*/
	@Override
	public String toString() {
		return "Calendar [id=" + id + ", type=" + type + ", calendarId=" + calendarId + ", calendarName=" + calendarName + ", title=" + title + ", description=" + description + ", start=" + start + ", end=" + end + ", createdByUserId=" + createdByUserId + ", " + super.toString() + "]";
	}
}
