package bbdn.rest.objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Membership {

	@JsonProperty("userId")
    private String userId;
	
	@JsonProperty("courseId")
	private String courseId;
	
	@JsonProperty("dataSourceId")
	private String dataSourceId;
	
	@JsonProperty("created")
    private String created;
	
	@JsonProperty("courseRoleId")
    private String courseRoleId;
	
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

    public Membership() {
    }

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getDataSourceId() {
		return dataSourceId;
	}

	public void setDataSourceId(String dataSourceId) {
		this.dataSourceId = dataSourceId;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String isCourseRoleId() {
		return courseRoleId;
	}

	public void setCourseRoleId(String courseRoleId) {
		this.courseRoleId = courseRoleId;
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
		return "Membership [userId=" + userId + ", courseId=" + courseId + ", dataSourceId=" + dataSourceId
				+ ", created=" + created + ", courseRoleId=" + courseRoleId + ", availability=" + availability
				+ ", status=" + status + ", code=" + code + ", message=" + message + ", developerMessage="
				+ developerMessage + ", extrainfo=" + extrainfo + "]";
	}
}
