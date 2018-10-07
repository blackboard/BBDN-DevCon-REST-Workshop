package bbdn.rest.course;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import bbdn.rest.common.Availability;
import bbdn.rest.course.Enrollment;
import bbdn.rest.course.Locale;

import bbdn.rest.BaseObject;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Course extends BaseObject {

	@JsonProperty("id")
  private String id;

	@JsonProperty("uuid")
  private String uuid;

	@JsonProperty("externalId")
	private String externalId;

	@JsonProperty("dataSourceId")
	private String dataSourceId;

	@JsonProperty("courseId")
	private String courseId;

	@JsonProperty("name")
	private String name;

	@JsonProperty("description")
  private String description;

	@JsonProperty("created")
  private String created;

	@JsonProperty("organization")
  private boolean organization;

	@JsonProperty("ultraStatus")
  private String ultraStatus;

	@JsonProperty("allowGuests")
  private boolean allowGuests;

	@JsonProperty("termId")
  private String termId;

	@JsonProperty("availability")
	private Availability availability;

	@JsonProperty("enrollment")
	private Enrollment enrollment;

	@JsonProperty("locale")
	private Locale locale;

	@JsonProperty("hasChildren")
	private boolean hasChildren;

	@JsonProperty("parentId")
	private String parentId;

	@JsonProperty("externalAccessUrl")
	private String externalAccessUrl;

	@JsonProperty("guestAccessUrl")
	private String guestAccessUrl;

	public Course() {
		super();
		this.availability = new Availability();
		this.enrollment = new Enrollment();
		this.locale = new Locale();
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
	* Returns value of uuid
	* @return
	*/
	public String getUuid() {
		return uuid;
	}

	/**
	* Sets new value of uuid
	* @param
	*/
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	/**
	* Returns value of externalId
	* @return
	*/
	public String getExternalId() {
		return externalId;
	}

	/**
	* Sets new value of externalId
	* @param
	*/
	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	/**
	* Returns value of dataSourceId
	* @return
	*/
	public String getDataSourceId() {
		return dataSourceId;
	}

	/**
	* Sets new value of dataSourceId
	* @param
	*/
	public void setDataSourceId(String dataSourceId) {
		this.dataSourceId = dataSourceId;
	}

	/**
	* Returns value of courseId
	* @return
	*/
	public String getCourseId() {
		return courseId;
	}

	/**
	* Sets new value of courseId
	* @param
	*/
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	/**
	* Returns value of name
	* @return
	*/
	public String getName() {
		return name;
	}

	/**
	* Sets new value of name
	* @param
	*/
	public void setName(String name) {
		this.name = name;
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
	* Returns value of created
	* @return
	*/
	public String getCreated() {
		return created;
	}

	/**
	* Sets new value of created
	* @param
	*/
	public void setCreated(String created) {
		this.created = created;
	}

	/**
	* Returns value of organization
	* @return
	*/
	public boolean isOrganization() {
		return organization;
	}

	/**
	* Sets new value of organization
	* @param
	*/
	public void setOrganization(boolean organization) {
		this.organization = organization;
	}

	/**
	* Returns value of ultraStatus
	* @return
	*/
	public String getUltraStatus() {
		return ultraStatus;
	}

	/**
	* Sets new value of ultraStatus
	* @param
	*/
	public void setUltraStatus(String ultraStatus) {
		this.ultraStatus = ultraStatus;
	}

	/**
	* Returns value of allowGuests
	* @return
	*/
	public boolean isAllowGuests() {
		return allowGuests;
	}

	/**
	* Sets new value of allowGuests
	* @param
	*/
	public void setAllowGuests(boolean allowGuests) {
		this.allowGuests = allowGuests;
	}

	/**
	* Returns value of termId
	* @return
	*/
	public String getTermId() {
		return termId;
	}

	/**
	* Sets new value of termId
	* @param
	*/
	public void setTermId(String termId) {
		this.termId = termId;
	}

	/**
	* Returns value of availability
	* @return
	*/
	public Availability getAvailability() {
		return availability;
	}

	/**
	* Sets new value of availability
	* @param
	*/
	public void setAvailability(Availability availability) {
		this.availability = availability;
	}

	/**
	* Returns value of enrollment
	* @return
	*/
	public Enrollment getEnrollment() {
		return enrollment;
	}

	/**
	* Sets new value of enrollment
	* @param
	*/
	public void setEnrollment(Enrollment enrollment) {
		this.enrollment = enrollment;
	}

	/**
	* Returns value of locale
	* @return
	*/
	public Locale getLocale() {
		return locale;
	}

	/**
	* Sets new value of locale
	* @param
	*/
	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	/**
	* Returns value of hasChildren
	* @return
	*/
	public boolean isHasChildren() {
		return hasChildren;
	}

	/**
	* Sets new value of hasChildren
	* @param
	*/
	public void setHasChildren(boolean hasChildren) {
		this.hasChildren = hasChildren;
	}

	/**
	* Returns value of parentId
	* @return
	*/
	public String getParentId() {
		return parentId;
	}

	/**
	* Sets new value of parentId
	* @param
	*/
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/**
	* Returns value of externalAccessUrl
	* @return
	*/
	public String getExternalAccessUrl() {
		return externalAccessUrl;
	}

	/**
	* Sets new value of externalAccessUrl
	* @param
	*/
	public void setExternalAccessUrl(String externalAccessUrl) {
		this.externalAccessUrl = externalAccessUrl;
	}

	/**
	* Returns value of guestAccessUrl
	* @return
	*/
	public String getGuestAccessUrl() {
		return guestAccessUrl;
	}

	/**
	* Sets new value of guestAccessUrl
	* @param
	*/
	public void setGuestAccessUrl(String guestAccessUrl) {
		this.guestAccessUrl = guestAccessUrl;
	}

	/**
	* Create string representation of Course for printing
	* @return
	*/
	@Override
	public String toString() {
		return "Course [id=" + id + ", uuid=" + uuid + ", externalId=" + externalId + ", dataSourceId=" + dataSourceId + ", courseId=" + courseId + ", name=" +
		name + ", description=" + description + ", created=" + created + ", organization=" + organization + ", ultraStatus=" + ultraStatus + ", allowGuests=" +
		allowGuests +
		", termId=" + termId +
		", " + availability.toString() +
		", " + enrollment.toString() +
		", " + locale.toString() +
		", hasChildren=" + hasChildren + ", parentId=" + parentId + ", externalAccessUrl=" + externalAccessUrl + ", guestAccessUrl=" +
		guestAccessUrl + ", " + super.toString() + "]";
	}
}
