package bbdn.rest.objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Course {

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
	
	@JsonProperty("readOnly")
    private boolean readOnly;
	
	@JsonProperty("termId")
    private String termId;
	
	@JsonProperty("availability")
	private Availability availability;
	
	@JsonProperty(access = Access.WRITE_ONLY)
    private String status;
	
	@JsonProperty(access = Access.WRITE_ONLY)
    private String code;
	
	@JsonProperty(access = Access.WRITE_ONLY)
    private String message;
	
	@JsonProperty(access = Access.WRITE_ONLY)
    private String developerMessage;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private String extrainfo;
	
    public Course() {
    }


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getUuid() {
		return uuid;
	}


	public void setUuid(String uuid) {
		this.uuid = uuid;
	}


	public String getExternalId() {
		return externalId;
	}


	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}


	public String getDataSourceId() {
		return dataSourceId;
	}


	public void setDataSourceId(String dataSourceId) {
		this.dataSourceId = dataSourceId;
	}


	public String getCourseId() {
		return courseId;
	}


	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getCreated() {
		return created;
	}


	public void setCreated(String created) {
		this.created = created;
	}


	public boolean isOrganization() {
		return organization;
	}


	public void setOrganization(boolean organization) {
		this.organization = organization;
	}


	public String getUltraStatus() {
		return ultraStatus;
	}


	public void setUltraStatus(String ultraStatus) {
		this.ultraStatus = ultraStatus;
	}


	public boolean isAllowGuests() {
		return allowGuests;
	}


	public void setAllowGuests(boolean allowGuests) {
		this.allowGuests = allowGuests;
	}


	public boolean isReadOnly() {
		return readOnly;
	}


	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}


	public String getTermId() {
		return termId;
	}


	public void setTermId(String termId) {
		this.termId = termId;
	}


	public Availability getAvailability() {
		return availability;
	}


	public void setAvailability(Availability availability) {
		this.availability = availability;
	}

	@JsonIgnore
	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}

	@JsonIgnore
	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}

	@JsonIgnore
	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}

	@JsonIgnore
	public String getDeveloperMessage() {
		return developerMessage;
	}


	public void setDeveloperMessage(String developerMessage) {
		this.developerMessage = developerMessage;
	}

	@JsonIgnore
	public String getExtrainfo() {
		return extrainfo;
	}


	public void setExtrainfo(String extrainfo) {
		this.extrainfo = extrainfo;
	}


	@Override
	public String toString() {
		return "Course [id=" + id + ", uuid=" + uuid + ", externalId=" + externalId + ", dataSourceId=" + dataSourceId
				+ ", courseId=" + courseId + ", name=" + name + ", description=" + description + ", created=" + created
				+ ", organization=" + organization + ", ultraStatus=" + ultraStatus + ", allowGuests=" + allowGuests
				+ ", readOnly=" + readOnly + ", termId=" + termId + ", availability=" + availability + ", status="
				+ status + ", code=" + code + ", message=" + message + ", developerMessage=" + developerMessage
				+ ", extrainfo=" + extrainfo + "]";
	}

}
