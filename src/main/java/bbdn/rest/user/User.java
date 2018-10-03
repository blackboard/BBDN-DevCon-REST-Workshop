package bbdn.rest.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import bbdn.rest.common.Availability;
import bbdn.rest.BaseObject;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User extends BaseObject {

	@JsonProperty("id")
  private String id;

	@JsonProperty("uuid")
  private String uuid;

	@JsonProperty("externalId")
	private String externalId;

	@JsonProperty("dataSourceId")
	private String dataSourceId;

	@JsonProperty("userName")
	private String userName;

	@JsonProperty("password")
	private String password;

	@JsonProperty("studentId")
	private String studentId;

	@JsonProperty("educationLevel")
  private String educationLevel;

	@JsonProperty("gender")
  private String gender;

	@JsonProperty("birthDate")
  private String birthDate;

	@JsonProperty("created")
  private String created;

	@JsonProperty("lastLogin")
  private String lastLogin;

	@JsonProperty("institutionRoleIds")
  private String[] institutionRoleIds;

	@JsonProperty("systemRoleIds")
  private String[] systemRoleIds;

	@JsonProperty("availability")
  private Availability availability;

	@JsonProperty("name")
  private Name name;

	@JsonProperty("contact")
  private Contact contact;

  public User() {
		super();
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getEducationLevel() {
		return educationLevel;
	}

	public void setEducationLevel(String educationLevel) {
		this.educationLevel = educationLevel;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public Availability getAvailability() {
		return availability;
	}

	public void setAvailability(Availability availability) {
		this.availability = availability;
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public String getLastLogin() {
		return lastLogin;
	}

	public String[] getInstitutionRoleIds() {
		return institutionRoleIds;
	}

	public void setInstitutionRoleIds(String[] institutionRoleIds) {
		this.institutionRoleIds = institutionRoleIds;
	}

	public String[] getSystemRoleIds() {
		return systemRoleIds;
	}

	public void setSystemRoleIds(String[] systemRoleIds) {
		this.systemRoleIds = systemRoleIds;
	}

	@Override
	public String toString() {
		String avl = availability != null ? availability.toString() : "null";
		String nm = name != null ? name.toString() : "null";
		String ct = contact != null ? contact.toString() : "null";
		String ll = lastLogin != null ? lastLogin : "null";
		return "User [id=" + id + ", uuid=" + uuid + ", externalId=" + externalId +
		", dataSourceId=" + dataSourceId	+ ", userName=" + userName +
		", password=" + password + ", studentId=" + studentId +
		", educationLevel=" + educationLevel + ", gender=" + gender +
		", birthDate=" + birthDate + ", created=" + created	+ ", name=" +
		nm + ", contact=" + ct + ", lastLogin=" + ll + ", " + super.toString() + "]";
	}

}
