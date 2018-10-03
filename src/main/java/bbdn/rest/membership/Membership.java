package bbdn.rest.membership;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import bbdn.rest.common.Availability;
import bbdn.rest.BaseObject;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Membership extends BaseObject {

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

	@Override
	public String toString() {
		String avl = availability != null ? availability.toString() : "null";
		return "Membership [userId=" + userId + ", courseId=" + courseId + ", dataSourceId=" + dataSourceId
				+ ", created=" + created + ", courseRoleId=" + courseRoleId + ", availability=" + avl
				+ ", " + super.toString() + "]";
	}
}
