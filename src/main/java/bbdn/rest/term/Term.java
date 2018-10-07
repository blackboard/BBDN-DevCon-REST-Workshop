package bbdn.rest.term;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import bbdn.rest.common.Availability;
import bbdn.rest.BaseObject;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Term extends BaseObject {

	@JsonProperty("id")
  private String id;

	@JsonProperty("externalId")
	private String externalId;

	@JsonProperty("dataSourceId")
	private String dataSourceId;

	@JsonProperty("name")
	private String name;

	@JsonProperty("description")
  private String description;

	@JsonProperty("availability")
  private Availability availability;

  public Term() {
		super();
  }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDataSourceId() {
		return dataSourceId;
	}

	public void setDataSourceId(String dataSourceId) {
		this.dataSourceId = dataSourceId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
		return "Term [id=" + id + ", externalId=" + externalId + ", dataSourceId=" + dataSourceId + ", name=" + name
				+ ", description=" + description +
				", availability=" + avl +
				", " + super.toString() + "]";
	}

}
